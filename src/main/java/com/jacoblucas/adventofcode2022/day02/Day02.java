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

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day02-input.txt");

        final List<Pair> matches = input.stream()
                .map(s -> s.split(" "))
                .map(arr -> new Pair(parseOpponent(arr[0]), parsePlayer(arr[1])))
                .toList();

        // Opponent is first column, you are the second column
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
    }
}
