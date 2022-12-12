package com.jacoblucas.adventofcode2022.day10;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day10Test {
    @Test
    public void testInvalidInstruction() {
        final Instruction invalid = Day10.parse("abc 123");
        assertThat(invalid, is(nullValue()));
    }

    @Test
    public void testParseAddx() {
        final Instruction addx = Day10.parse("addx 5");
        assertThat(addx.name(), is("addx"));
        assertThat(addx.value(), is(5));
        assertThat(addx.cyclesToComplete(), is(2));
    }

    @Test
    public void testParseNoop() {
        final Instruction addx = Day10.parse("noop");
        assertThat(addx.name(), is("noop"));
        assertThat(addx.value(), is(0));
        assertThat(addx.cyclesToComplete(), is(1));
    }
}
