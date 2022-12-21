package com.jacoblucas.adventofcode2022.day21;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
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
    }

    public static long resolve(final String name, final List<String> input) {
        if (monkeyNumberMap.containsKey(name)) {
            return monkeyNumberMap.get(name);
        }

        final String inputLine = input.stream()
                .filter(str -> str.startsWith(name))
                .findFirst()
                .orElse("");

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
}
