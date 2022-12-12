package com.jacoblucas.adventofcode2022.day10;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddxInstructionTest {
    @Test
    public void testParse() {
        final Instruction instruction = new AddxInstruction("addx", 5, 2);
        assertThat(instruction.name(), is("addx"));
        assertThat(instruction.value(), is(5));
        assertThat(instruction.cyclesToComplete(), is(2));
    }
}
