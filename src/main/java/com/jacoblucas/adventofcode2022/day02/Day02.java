package com.jacoblucas.adventofcode2022.day02;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.List;

import static com.jacoblucas.adventofcode2022.day02.Shape.RESULT_MAP;

public class Day02 {
    public static Shape getWinner(final Pair<Shape, Shape> match) {
        return RESULT_MAP.get(match);
    }

    public static Shape getShapeToThrow(final Pair<Shape, Shape> match) {
        final Shape opponent = match.first();

        // X/ROCK means you need to lose,
        // Y/PAPER means you need to end the round in a draw, and
        // Z/SCISSORS means you need to win
        final Shape result = match.second();

        switch (result) {
            case ROCK -> {
                // what loses to the opponents throw?
                return RESULT_MAP.entrySet().stream()
                        .filter(e -> e.getKey().second() == opponent && e.getValue() == opponent)
                        .map(e -> e.getKey().first())
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No configured result to lose against: " + opponent));
            }
            case PAPER -> {
                // what draws with the opponents throw?
                return opponent;
            }
            case SCISSORS -> {
                // what beats the opponents throw?
                return RESULT_MAP.entrySet().stream()
                        .filter(e -> e.getKey().first() == opponent && e.getValue() != opponent)
                        .map(e -> e.getKey().second())
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No configured result to win against: " + opponent));
            }
            default -> throw new IllegalStateException("Unexpected shape: " + result);
        }
    }

    // Opponent is first column, you are the second column
    public static int score(final Pair<Shape, Shape> match) {
        final Shape winner = getWinner(match);

        int score = match.second().getScore();
        if (winner == null)
            score += 3;
        else if (winner == match.second())
            score += 6;

        return score;
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day02-input.txt");

        final List<Pair<Shape, Shape>> matches = input.stream()
                .map(s -> s.split(" "))
                .map(arr -> new Pair<>(Shape.parse(arr[0]), Shape.parse(arr[1])))
                .toList();

        // Part 1
        int score = matches.stream()
                .mapToInt(Day02::score)
                .sum();
        System.out.println(score);

        // Part 2
        score = matches.stream()
                .map(p -> new Pair<>(p.first(), getShapeToThrow(p)))
                .mapToInt(Day02::score)
                .sum();
        System.out.println(score);
    }
}
