package com.jacoblucas.adventofcode2022.day04;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2022.utils.Pair;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day04Test {
    private static final List<String> INPUT = ImmutableList.of(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8");

    @Test
    public void testParseInput() {
        assertThat(Day04.parseInput(INPUT), is(ImmutableList.of(
                new Pair<>(ImmutableList.of(2,3,4), ImmutableList.of(6,7,8)),
                new Pair<>(ImmutableList.of(2,3), ImmutableList.of(4,5)),
                new Pair<>(ImmutableList.of(5,6,7), ImmutableList.of(7,8,9)),
                new Pair<>(ImmutableList.of(2,3,4,5,6,7,8), ImmutableList.of(3,4,5,6,7)),
                new Pair<>(ImmutableList.of(6), ImmutableList.of(4,5,6)),
                new Pair<>(ImmutableList.of(2,3,4,5,6), ImmutableList.of(4,5,6,7,8))
        )));
    }

    @Test
    public void testIsContainedBy() {
        final List<Pair<List<Integer>, List<Integer>>> pairs = Day04.parseInput(INPUT);

        assertThat(Day04.isContainedBy(pairs.get(0).first(), pairs.get(0).second()), is(false));
        assertThat(Day04.isContainedBy(pairs.get(1).first(), pairs.get(1).second()), is(false));
        assertThat(Day04.isContainedBy(pairs.get(2).first(), pairs.get(2).second()), is(false));
        assertThat(Day04.isContainedBy(pairs.get(3).first(), pairs.get(3).second()), is(true));
    }

    @Test
    public void testCountFullyContained() {
        long count = Day04.countFullyContained(Day04.parseInput(INPUT));
        assertThat(count, is(2L));
    }

    @Test
    public void testOverlaps() {
        final List<Pair<List<Integer>, List<Integer>>> pairs = Day04.parseInput(INPUT);

        assertThat(Day04.overlaps(pairs.get(0).first(), pairs.get(0).second()), is(false));
        assertThat(Day04.overlaps(pairs.get(1).first(), pairs.get(1).second()), is(false));
        assertThat(Day04.overlaps(pairs.get(2).first(), pairs.get(2).second()), is(true));
        assertThat(Day04.overlaps(pairs.get(3).first(), pairs.get(3).second()), is(true));
        assertThat(Day04.overlaps(pairs.get(4).first(), pairs.get(4).second()), is(true));
        assertThat(Day04.overlaps(pairs.get(5).first(), pairs.get(5).second()), is(true));
    }

    @Test
    public void testCountOverlaps() {
        long count = Day04.countOverlaps(Day04.parseInput(INPUT));
        assertThat(count, is(4L));
    }
}
