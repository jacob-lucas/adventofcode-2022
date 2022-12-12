package com.jacoblucas.adventofcode2022.day10;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Day10 {
    public static Instruction parse(final String str) {
        final String[] parts = str.split(" ");
        return switch (parts[0]) {
            case "addx" -> new AddxInstruction(parts[0], Integer.parseInt(parts[1]), 2);
            case "noop" -> new NoopInstruction(parts[0], 0, 1);
            default -> null;
        };
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day10-input.txt");

        final List<Instruction> instructions = input.stream()
                .map(Day10::parse)
                .filter(Objects::nonNull)
                .toList();

        final CPU cpu = new CPU();

        // Part 1
        cpu.execute(instructions);
        System.out.println(cpu.getSignalStrength());

        // Part 2
        CRT.display();
    }
}
