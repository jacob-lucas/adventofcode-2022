package com.jacoblucas.adventofcode2022.day05;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class Day05 {
    static Map<Integer, Stack<Character>> STACK_MAP = new HashMap<>();
    static List<Instruction> INSTRUCTIONS = new ArrayList<>();

    public static void initialise(List<String> input) {
        int splitPoint = 0;
        for (int i=0; i<input.size(); i++) {
            if (input.get(i).isBlank()) {
                // this is the break between stacks and instructions
                splitPoint = i;
                break;
            }
        }
        STACK_MAP = parseStacks(input.subList(0, splitPoint));
        INSTRUCTIONS = input.subList(splitPoint + 1, input.size()).stream()
                .map(Instruction::parse)
                .toList();
    }

    public static Map<Integer, Stack<Character>> parseStacks(List<String> stackInput) {
        final Map<Integer, Stack<Character>> stacks = new HashMap<>();

        // create the stacks
        final String stackIds = stackInput.get(stackInput.size() - 1).replaceAll(" ", "");
        for (char c : stackIds.toCharArray()) {
            stacks.put(Integer.valueOf(""+c), new Stack<>());
        }

        final String stackIdLine = stackInput.get(stackInput.size() - 1);

        // add the contents onto each stack
        for (int i = stackInput.size() - 2; i >= 0; i--) {
            final Set<Integer> ids = stacks.keySet();
            for (int id : ids) {
                int index = stackIdLine.indexOf(""+id);
                final Stack<Character> stack = stacks.get(id);
                final String line = stackInput.get(i);

                if (index > line.length()) {
                    continue;
                }

                final char ch = line.charAt(index);
                if (ch != ' ') {
                    stack.push(ch);
                }
            }
        }

        return stacks;
    }

    public static void execute(final List<Instruction> instructions, final Map<Integer, Stack<Character>> stacks) {
        for (final Instruction i : instructions) {
            IntStream.range(0, i.n()).forEach(n -> {
                final Stack<Character> fromStack = stacks.get(i.from());
                final Stack<Character> toStack = stacks.get(i.to());
                toStack.push(fromStack.pop());
            });
        }
    }

    public static void executeCrateMover9001(final List<Instruction> instructions, final Map<Integer, Stack<Character>> stacks) {
        for (final Instruction i : instructions) {
            Stack<Character> tmp = new Stack<>();
            IntStream.range(0, i.n()).forEach(n -> {
                final Stack<Character> fromStack = stacks.get(i.from());
                tmp.push(fromStack.pop());
            });
            IntStream.range(0, i.n()).forEach(n -> {
                final Stack<Character> toStack = stacks.get(i.to());
                toStack.push(tmp.pop());
            });
        }
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day05-input.txt");
        initialise(input);

        // Part 1
        execute(INSTRUCTIONS, STACK_MAP);
        System.out.println(toStackString());

        // Part 2
        initialise(input);
        executeCrateMover9001(INSTRUCTIONS, STACK_MAP);
        System.out.println(toStackString());
    }

    static String toStackString() {
        StringBuilder sb = new StringBuilder();
        for (final int i : STACK_MAP.keySet()) {
            sb.append(STACK_MAP.get(i).peek());
        }
        return sb.toString();
    }
}
