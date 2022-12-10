package com.jacoblucas.adventofcode2022.day08;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day08Test {
    public static final List<String> INPUT = ImmutableList.of(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390");

    @Test
    public void testParse() {
        final Tree[][] parsed = Day08.parse(INPUT);

        assertThat(parsed.length, is(5));
        assertThat(parsed[0], is(new Tree[]{
                new Tree(3),
                new Tree(0),
                new Tree(3),
                new Tree(7),
                new Tree(3)}));
        assertThat(parsed[1], is(new Tree[]{
                new Tree(2),
                new Tree(5),
                new Tree(5),
                new Tree(1),
                new Tree(2)}));
        assertThat(parsed[2], is(new Tree[]{
                new Tree(6),
                new Tree(5),
                new Tree(3),
                new Tree(3),
                new Tree(2)}));
        assertThat(parsed[3], is(new Tree[]{
                new Tree(3),
                new Tree(3),
                new Tree(5),
                new Tree(4),
                new Tree(9)}));
        assertThat(parsed[4], is(new Tree[]{
                new Tree(3),
                new Tree(5),
                new Tree(3),
                new Tree(9),
                new Tree(0)}));
    }

    @Test
    public void testIsVisibleOffTheGrid() {
        final Tree[][] map = Day08.parse(INPUT);
        assertThat(Day08.isVisible(map, -1, 1), is(false));
        assertThat(Day08.isVisible(map, 1, -1), is(false));
        assertThat(Day08.isVisible(map, 1, map.length + 1), is(false));
        assertThat(Day08.isVisible(map, map[0].length + 1, -1), is(false));
    }

    @Test
    public void testIsVisible() {
        final Tree[][] map = Day08.parse(INPUT);

        assertThat(Day08.isVisible(map, 1, 1), is(true));
        assertThat(Day08.isVisible(map, 2, 1), is(true));
        assertThat(Day08.isVisible(map, 3, 1), is(false));
        assertThat(Day08.isVisible(map, 1, 2), is(true));
        assertThat(Day08.isVisible(map, 2, 2), is(false));
        assertThat(Day08.isVisible(map, 3, 2), is(true));
        assertThat(Day08.isVisible(map, 1, 3), is(false));
        assertThat(Day08.isVisible(map, 2, 3), is(true));
        assertThat(Day08.isVisible(map, 3, 3), is(false));
    }

    @Test
    public void testCenter3() {
        final Tree[][] map = Day08.parse(INPUT);
        assertThat(Day08.isVisible(map, 2, 2, 3, -1, 0), is(false));
        assertThat(Day08.isVisible(map, 2, 2, 3, 1, 0), is(false));
        assertThat(Day08.isVisible(map, 2, 2, 3, 0, 1), is(false));
        assertThat(Day08.isVisible(map, 2, 2, 3, 0, -1), is(false));
    }

    @Test
    public void testCountVisible() {
        final Tree[][] map = Day08.parse(INPUT);
        assertThat(Day08.countVisible(map), is(21));
    }

    @Test
    public void testScenicScore() {
        final Tree[][] map = Day08.parse(INPUT);
        assertThat(Day08.scenicScore(map, 2, 1), is(4L));
        assertThat(Day08.scenicScore(map, 2, 3), is(8L));
    }

    @Test
    public void testScenicScoreMiddle5() {
        final Tree[][] map = Day08.parse(INPUT);
        assertThat(Day08.scenicScore(map, 2, 1, 5, -1, 0, 0), is(1L));
        assertThat(Day08.scenicScore(map, 2, 1, 5, 1, 0, 0), is(2L));
        assertThat(Day08.scenicScore(map, 2, 1, 5, 0, -1, 0), is(1L));
        assertThat(Day08.scenicScore(map, 2, 1, 5, 0, 1, 0), is(2L));
    }

    @Test
    public void testMaxScenicScore() {
        final Tree[][] map = Day08.parse(INPUT);
        long maxScenicScore = Day08.maxScenicScore(map);
        assertThat(maxScenicScore, is(8L));
    }
}
