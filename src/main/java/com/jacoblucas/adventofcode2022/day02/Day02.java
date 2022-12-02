package com.jacoblucas.adventofcode2022.day02;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.List;

import static com.jacoblucas.adventofcode2022.day02.Shape.PAPER;
import static com.jacoblucas.adventofcode2022.day02.Shape.ROCK;
import static com.jacoblucas.adventofcode2022.day02.Shape.SCISSORS;

public class Day02 {
    public static Shape parseOpponent(final String s) {
        return switch(s) {
            case "A" -> ROCK;
            case "B" -> Shape.PAPER;
            case "C" -> Shape.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    public static Shape parsePlayer(final String s) {
        return switch(s) {
            case "X" -> ROCK;
            case "Y" -> Shape.PAPER;
            case "Z" -> Shape.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    public static Shape getWinner(final Pair<Shape, Shape> match) {
        if (match.t() == ROCK) {
            return switch (match.u()) {
                case PAPER -> PAPER;
                case SCISSORS -> ROCK;
                case ROCK -> null;
            };
        } else if (match.t() == PAPER) {
            return switch (match.u()) {
                case PAPER -> null;
                case SCISSORS -> SCISSORS;
                case ROCK -> PAPER;
            };
        } else if (match.t() == SCISSORS) {
            return switch (match.u()) {
                case PAPER -> SCISSORS;
                case SCISSORS -> null;
                case ROCK -> ROCK;
            };
        } else {
            return null;
        }
    }

    public static Shape getShapeToThrow(final Pair<Shape, Shape> match) {
        // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
        if (match.t() == ROCK) {
            return switch (match.u()) {
                case ROCK -> SCISSORS; // lose
                case PAPER -> ROCK; // draw
                case SCISSORS -> PAPER; // win
            };
        } else if (match.t() == PAPER) { // Y
            return switch (match.u()) {
                case ROCK -> ROCK; // lose
                case PAPER -> PAPER; // draw
                case SCISSORS -> SCISSORS; // win
            };
        } else if (match.t() == SCISSORS) { // Z
            return switch (match.u()) {
                case ROCK -> PAPER; // lose
                case PAPER -> SCISSORS; // draw
                case SCISSORS -> ROCK; // win
            };
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day02-input.txt");

        final List<Pair> matches = input.stream()
                .map(s -> s.split(" "))
                .map(arr -> new Pair(parseOpponent(arr[0]), parsePlayer(arr[1])))
                .toList();

        // Opponent is first column, you are the second column
        // Part 1
        int score = 0;
        for (Pair match : matches) {
            final Shape winner = getWinner(match);
            if (winner == null)
                score += 3;

            if (winner == match.u()) {
                score += winner.getScore() + 6;
            } else {
                score += ((Shape) match.u()).getScore();
            }
        }
        System.out.println(score);

        // Opponent is first column, second column is how the round should end
        // Part 2
        score = 0;
        for (Pair match : matches) {
            final Shape thrown = getShapeToThrow(match);
            final Shape result = ((Shape) match.u());

            if (result == PAPER) // draw
                score += 3;

            if (result == SCISSORS) {
                // win
                score += thrown.getScore() + 6;
            } else {
                score += thrown.getScore();
            }
        }
        System.out.println(score);
    }
}
