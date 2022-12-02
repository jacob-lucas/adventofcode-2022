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
}
