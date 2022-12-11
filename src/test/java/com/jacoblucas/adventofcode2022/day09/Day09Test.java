package com.jacoblucas.adventofcode2022.day09;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day09Test {
    private static final List<String> INPUT = ImmutableList.of(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2");

    private static final List<String> LARGER_INPUT = ImmutableList.of(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20");

    @Test
    public void testAllExampleInput() {
        final Bridge bridge = new Bridge(2);
        Day09.simulate(bridge, INPUT);

        assertThat(bridge.getTailLocationMap().size(), is(13));
    }

    @Test
    public void testTenKnots() {
        final Bridge bridge = new Bridge(10);
        Day09.simulate(bridge, LARGER_INPUT);

        assertThat(bridge.getTailLocationMap().size(), is(36));
    }
}
