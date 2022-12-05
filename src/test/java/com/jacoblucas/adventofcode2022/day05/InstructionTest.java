package com.jacoblucas.adventofcode2022.day05;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstructionTest {
    @Test
    public void testParse() {
        final Instruction instruction = Instruction.parse("move 1 from 2 to 3");
        assertThat(instruction.n(), is(1));
        assertThat(instruction.from(), is(2));
        assertThat(instruction.to(), is(3));
    }
}
