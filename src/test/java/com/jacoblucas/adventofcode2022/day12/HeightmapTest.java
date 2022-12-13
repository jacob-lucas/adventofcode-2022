package com.jacoblucas.adventofcode2022.day12;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.jacoblucas.adventofcode2022.utils.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class HeightmapTest {
    private static final List<String> TEST_INPUT = ImmutableList.of(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi");

    private Heightmap heightmap;

    @Before
    public void setUp() {
        heightmap = new Heightmap(TEST_INPUT);
    }

    @Test
    public void testFindStart() {
        assertThat(heightmap.start(), is(new Pair<>(0,0)));
    }

    @Test
    public void testFindEnd() {
        assertThat(heightmap.end(), is(new Pair<>(2,5)));
    }

    @Test
    public void testValidSteps() {
        assertThat(heightmap.validSteps(new Pair<>(0,0), ImmutableSet.of()), containsInAnyOrder(
                new Pair<>(1,0),
                new Pair<>(0,1)
        ));
        assertThat(heightmap.validSteps(new Pair<>(2,2), ImmutableSet.of(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2))), containsInAnyOrder(
                new Pair<>(2,1),
                new Pair<>(3,2)
        ));
        assertThat(heightmap.validSteps(new Pair<>(3,2), ImmutableSet.of(new Pair<>(0,0), new Pair<>(0,1), new Pair<>(0,2), new Pair<>(1,2), new Pair<>(2,1), new Pair<>(2,2), new Pair<>(3,1), new Pair<>(4,2))), is(ImmutableList.of()));
    }

    @Test
    public void testFindPath() {
        final List<Pair<Integer, Integer>> path = heightmap.findPath(new Pair<>(0, 0), new Pair<>(2, 5));
        path.forEach(System.out::println);
        assertThat(path.size() - 1, is(31));
    }

    @Test
    public void testFindShortestPath() {
        final List<List<Pair<Integer, Integer>>> paths = heightmap.findShortestPath(new Pair<>(2, 5));
        paths.forEach(System.out::println);
        assertThat(paths.get(0).size() - 1, is(29));
    }
}
