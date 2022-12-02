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
    private final List<Pair> matches = Arrays.asList(
            new Pair(ROCK, PAPER),
            new Pair(PAPER, ROCK),
            new Pair(SCISSORS, SCISSORS));

    @Test
    public void testGetWinner() {
        assertThat(Day02.getWinner(matches.get(0)), is(PAPER));
        assertThat(Day02.getWinner(matches.get(1)), is(PAPER));
        assertThat(Day02.getWinner(matches.get(2)), is(nullValue()));
    }

    @Test
    public void testGetShapeToThrow() {
        assertThat(Day02.getShapeToThrow(matches.get(0)), is(ROCK));
        assertThat(Day02.getShapeToThrow(matches.get(1)), is(ROCK));
        assertThat(Day02.getShapeToThrow(matches.get(2)), is(ROCK));
    }

    @Test
    public void testScoreV1() {
        assertThat(Day02.scoreV1(matches.get(0)), is(8));
        assertThat(Day02.scoreV1(matches.get(1)), is(1));
        assertThat(Day02.scoreV1(matches.get(2)), is(6));
    }

    @Test
    public void testScoreV2() {
        assertThat(Day02.scoreV2(matches.get(0)), is(4));
        assertThat(Day02.scoreV2(matches.get(1)), is(1));
        assertThat(Day02.scoreV2(matches.get(2)), is(7));
    }
}
