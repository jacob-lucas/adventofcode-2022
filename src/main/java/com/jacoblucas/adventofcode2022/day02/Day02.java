package com.jacoblucas.adventofcode2022.day02;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.List;

import static com.jacoblucas.adventofcode2022.day02.Shape.PAPER;
import static com.jacoblucas.adventofcode2022.day02.Shape.ROCK;
import static com.jacoblucas.adventofcode2022.day02.Shape.SCISSORS;

public class Day02 {
    public static Shape getWinner(final Pair<Shape, Shape> match) {
        if (match.first() == ROCK) {
            return switch (match.second()) {
                case PAPER -> PAPER;
                case SCISSORS -> ROCK;
                case ROCK -> null;
            };
        } else if (match.first() == PAPER) {
            return switch (match.second()) {
                case PAPER -> null;
                case SCISSORS -> SCISSORS;
                case ROCK -> PAPER;
            };
        } else if (match.first() == SCISSORS) {
            return switch (match.second()) {
                case PAPER -> SCISSORS;
                case SCISSORS -> null;
                case ROCK -> ROCK;
            };
        } else {
            return null;
        }
    }

    // X/ROCK means you need to lose,
    // Y/PAPER means you need to end the round in a draw, and
    // Z/SCISSORS means you need to win
    public static Shape getShapeToThrow(final Pair<Shape, Shape> match) {
        if (match.first() == ROCK) {
            return switch (match.second()) { // X
                case ROCK -> SCISSORS; // lose
                case PAPER -> ROCK; // draw
                case SCISSORS -> PAPER; // win
            };
        } else if (match.first() == PAPER) { // Y
            return switch (match.second()) {
                case ROCK -> ROCK; // lose
                case PAPER -> PAPER; // draw
                case SCISSORS -> SCISSORS; // win
            };
        } else if (match.first() == SCISSORS) { // Z
            return switch (match.second()) {
                case ROCK -> PAPER; // lose
                case PAPER -> SCISSORS; // draw
                case SCISSORS -> ROCK; // win
            };
        } else {
            return null;
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
