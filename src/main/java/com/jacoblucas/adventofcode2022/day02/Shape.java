package com.jacoblucas.adventofcode2022.day02;

import com.google.common.collect.ImmutableMap;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.util.Map;

public enum Shape {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    // Left is defeated by Right, anything not listed returns null (draw)
    public static final Map<Pair<Shape, Shape>, Shape> RESULT_MAP = ImmutableMap.of(
            // Paper beats Rock
            new Pair<>(ROCK, PAPER), PAPER,
            new Pair<>(PAPER, ROCK), PAPER,

            // Rock beats Scissors
            new Pair<>(ROCK, SCISSORS), ROCK,
            new Pair<>(SCISSORS, ROCK), ROCK,

            // Scissors beats Paper
            new Pair<>(PAPER, SCISSORS), SCISSORS,
            new Pair<>(SCISSORS, PAPER), SCISSORS
    );

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
