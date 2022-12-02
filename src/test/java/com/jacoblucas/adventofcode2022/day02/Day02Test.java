package com.jacoblucas.adventofcode2022.day02;

import com.jacoblucas.adventofcode2022.utils.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jacoblucas.adventofcode2022.day02.Shape.PAPER;
import static com.jacoblucas.adventofcode2022.day02.Shape.ROCK;
import static com.jacoblucas.adventofcode2022.day02.Shape.SCISSORS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day02Test {
    @Test
    public void testGetWinner() {
        final List<Pair> matches = Arrays.asList(
                new Pair(ROCK, PAPER), // WIN
                new Pair(PAPER, ROCK), // LOSE
                new Pair(SCISSORS, SCISSORS)); // DRAW

        assertThat(Day02.getWinner(matches.get(0)), is(PAPER));
        assertThat(Day02.getWinner(matches.get(1)), is(PAPER));
        assertThat(Day02.getWinner(matches.get(2)), is(nullValue()));
    }

    @Test
    public void testGetShapeToThrow() {
        final List<Pair> matches = Arrays.asList(
                new Pair(ROCK, PAPER), // DRAW
                new Pair(PAPER, ROCK), // LOSE
                new Pair(SCISSORS, SCISSORS)); // WIN

        assertThat(Day02.getShapeToThrow(matches.get(0)), is(ROCK));
        assertThat(Day02.getShapeToThrow(matches.get(1)), is(ROCK));
        assertThat(Day02.getShapeToThrow(matches.get(2)), is(ROCK));
    }
}
