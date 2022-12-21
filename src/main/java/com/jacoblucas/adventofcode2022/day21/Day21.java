package com.jacoblucas.adventofcode2022.day21;

import com.google.common.collect.Lists;
import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {
    public static Map<String, Long> monkeyNumberMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day21-input.txt");

        // Part 1
        long number = resolve("root", input);
        System.out.println(monkeyNumberMap);
        System.out.println(number);

        // Part 2
        System.out.println(solveFor("humn", input));
    }

    public static long resolve(final String name, final List<String> input) {
        if (monkeyNumberMap.containsKey(name)) {
            return monkeyNumberMap.get(name);
        }

        final String inputLine = find(name + ":", input);

        final String job = inputLine.split(":")[1].trim();

        try {
            long number = Long.parseLong(job);
            monkeyNumberMap.put(name, number);
            return number;
        } catch (NumberFormatException nfe) {
            final String[] parts = job.split(" ");
            final String left = parts[0];
            final String op = parts[1];
            final String right = parts[2];

            return switch (op) {
                case "+" -> resolve(left, input) + resolve(right, input);
                case "-" -> resolve(left, input) - resolve(right, input);
                case "*" -> resolve(left, input) * resolve(right, input);
                case "/" -> resolve(left, input) / resolve(right, input);
                default -> -1;
            };
        }
    }

    public static long solveFor(final String solveFor, final List<String> input) {
        List<String> path = new ArrayList<>();
        path.add(solveFor);

        final String root = find("root:", input);
        String monkey = solveFor;
        String line = "";
        while (!line.equals(root)) {
            line = find(" " + monkey, input);
            final String m = line.split(":")[0];
            path.add(m);
            monkey = m;
        }

        path = Lists.reverse(path);

        System.out.println("Path to " + solveFor + " = " + path);

        String[] equation = line.split(":")[1].trim().split(" ");
        String left = equation[0];
        String right = equation[2];

        String next = path.contains(left) ? left : right;
        String lookup = path.contains(left) ? right : left;

        return solveForHelper(solveFor, input, find(next+":", input), path, resolve(lookup, input));
    }

    public static long solveForHelper(String solveFor, List<String> input, String line, List<String> path, long value) {
        String name = line.split(":")[0];
        if (name.equals(solveFor)) {
            return value;
        }

        String[] equation = line.split(":")[1].trim().split(" ");
        String left = equation[0];
        String op = equation[1];
        String right = equation[2];

        String next = path.contains(left) ? left : right;
        String lookup = path.contains(left) ? right : left;

        long lookupValue = resolve(lookup, input);

        // invert the operation
        long result = switch (op) {
            case "+" -> value - lookupValue;
            case "-" -> path.contains(left) ? value + lookupValue : lookupValue - value;
            case "*" -> value / lookupValue;
            case "/" -> path.contains(left) ? value * lookupValue : lookupValue / value;
            default -> -1;
        };

        return solveForHelper(solveFor, input, find(next+":", input), path, result);
    }

    private static String find(String str, List<String> input) {
        return input.stream()
                .filter(s -> s.contains(str))
                .findFirst()
                .orElse("");
    }
}
