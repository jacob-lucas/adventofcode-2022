package com.jacoblucas.adventofcode2022.day05;

public record Instruction(int n, int from, int to) {
    // move 1 from 2 to 1
    public static Instruction parse(final String str) {
        final String[] parts = str.split(" ");
        return new Instruction(
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[5]));
    }
}
