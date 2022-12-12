package com.jacoblucas.adventofcode2022.day10;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2022.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CPUTest {
    private CPU cpu;

    @Before
    public void setUp() {
        cpu = new CPU();
    }

    @Test
    public void testGetInvalidRegister() {
        assertThat(cpu.get("abc"), is(-1));
    }

    @Test
    public void testCycleWithNoop() {
        assertThat(cpu.get("X"), is(1));

        final NoopInstruction instruction = new NoopInstruction("noop", 0, 1);
        cpu.pre(instruction);
        cpu.tick(instruction);
        cpu.post(instruction);

        assertThat(cpu.get("X"), is(1));
        assertThat(cpu.getCycle(), is(1));
    }

    @Test
    public void testSmallProgram() {
        assertThat(cpu.get("X"), is(1));

        final NoopInstruction noop = new NoopInstruction("noop", 0, 1);
        final AddxInstruction addx3 = new AddxInstruction("addx", 3, 2);
        final AddxInstruction addxNeg3 = new AddxInstruction("addx", -5, 2);

        cpu.execute(ImmutableList.of(noop, addx3, addxNeg3));

        assertThat(cpu.get("X"), is(-1));
    }

    @Test
    public void testExecute() throws IOException {
        final List<Instruction> testInstructions = InputReader.readFile("src/test/resources/","day10-test-input.txt").stream()
                .map(Day10::parse)
                .filter(Objects::nonNull)
                .toList();

        cpu.execute(testInstructions);
        assertThat(cpu.getSignalStrength(), is(13140));
    }
}
