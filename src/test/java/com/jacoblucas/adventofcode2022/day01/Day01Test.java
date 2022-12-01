package com.jacoblucas.adventofcode2022.day01;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day01Test {
    @Test
    public void testGetTopN() {
        final List<Integer> ints = Arrays.asList(1,2,3,4,5);
        final IntStream topN = Day01.getTopN(ints, 3);
        assertThat(topN.sum(), is(12));
    }

    @Test
    public void testGetTopNEmptyList() {
        final List<Integer> ints = Collections.emptyList();
        final IntStream topN = Day01.getTopN(ints, 3);
        assertThat(topN.sum(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTopNNegativeN() {
        final List<Integer> ints = Collections.emptyList();
        final IntStream topN = Day01.getTopN(ints, -3);
        assertThat(topN.sum(), is(0));
    }
}
