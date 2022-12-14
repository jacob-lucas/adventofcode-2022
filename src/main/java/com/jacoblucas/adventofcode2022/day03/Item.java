package com.jacoblucas.adventofcode2022.day03;

public record Item(char character) {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public int getPriority() {
        final int idx = ALPHABET.indexOf(Character.toLowerCase(character));
        if (Character.isUpperCase(character)) {
            return 27 + idx;
        } else {
            return 1 + idx;
        }
    }
}
