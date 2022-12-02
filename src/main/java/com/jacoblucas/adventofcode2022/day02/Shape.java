package com.jacoblucas.adventofcode2022.day02;

public enum Shape {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;

    Shape(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static Shape parse(final String str) {
        return switch(str) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + str);
        };
    }
}
