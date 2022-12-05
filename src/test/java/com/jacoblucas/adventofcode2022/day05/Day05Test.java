package com.jacoblucas.adventofcode2022.day05;

import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day05Test {
    @Before
    public void setUp() {
        final List<String> input = ImmutableList.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 ",
                "",
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        );

        Day05.initialise(input);
    }

    @After
    public void tearDown() {
        Day05.STACK_MAP = null;
        Day05.INSTRUCTIONS = null;
    }

    @Test
    public void testInitialise() {
        Day05.STACK_MAP = null;
        Day05.INSTRUCTIONS = null;
        final List<String> input = ImmutableList.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 ",
                "",
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        );

        Day05.initialise(input);

        final Stack<Character> s1 = new Stack<>();
        s1.push('Z');
        s1.push('N');

        final Stack<Character> s2 = new Stack<>();
        s2.push('M');
        s2.push('C');
        s2.push('D');

        final Stack<Character> s3 = new Stack<>();
        s3.push('P');

        assertThat(Day05.STACK_MAP.get(1), is(s1));
        assertThat(Day05.STACK_MAP.get(2), is(s2));
        assertThat(Day05.STACK_MAP.get(3), is(s3));

        assertThat(Day05.INSTRUCTIONS, is(ImmutableList.of(
                new Instruction(1,2,1),
                new Instruction(3,1,3),
                new Instruction(2,2,1),
                new Instruction(1,1,2)
        )));
    }

    @Test
    public void testParseStacks() {
        final List<String> input = ImmutableList.of(
                "    [D]",
                "[N] [C]",
                "[Z] [M] [P]",
                " 1   2   3");

        final Stack<Character> s1 = new Stack<>();
        s1.push('Z');
        s1.push('N');

        final Stack<Character> s2 = new Stack<>();
        s2.push('M');
        s2.push('C');
        s2.push('D');

        final Stack<Character> s3 = new Stack<>();
        s3.push('P');

        final Map<Integer, Stack<Character>> stacks = Day05.parseStacks(input);
        assertThat(stacks.get(1), is(s1));
        assertThat(stacks.get(2), is(s2));
        assertThat(stacks.get(3), is(s3));
    }

    @Test
    public void testExecute() {
        Day05.execute(Day05.INSTRUCTIONS, Day05.STACK_MAP);

        final Stack<Character> expectedS1 = new Stack<>();
        expectedS1.push('C');

        final Stack<Character> expectedS2 = new Stack<>();
        expectedS2.push('M');

        final Stack<Character> expectedS3 = new Stack<>();
        expectedS3.push('P');
        expectedS3.push('D');
        expectedS3.push('N');
        expectedS3.push('Z');

        assertThat(Day05.STACK_MAP.get(1), is(expectedS1));
        assertThat(Day05.STACK_MAP.get(2), is(expectedS2));
        assertThat(Day05.STACK_MAP.get(3), is(expectedS3));
    }

    @Test
    public void testExecuteCrateMover9001() {
        Day05.executeCrateMover9001(Day05.INSTRUCTIONS, Day05.STACK_MAP);

        final Stack<Character> expectedS1 = new Stack<>();
        expectedS1.push('M');

        final Stack<Character> expectedS2 = new Stack<>();
        expectedS2.push('C');

        final Stack<Character> expectedS3 = new Stack<>();
        expectedS3.push('P');
        expectedS3.push('Z');
        expectedS3.push('N');
        expectedS3.push('D');

        assertThat(Day05.STACK_MAP.get(1), is(expectedS1));
        assertThat(Day05.STACK_MAP.get(2), is(expectedS2));
        assertThat(Day05.STACK_MAP.get(3), is(expectedS3));
    }

    @Test
    public void testToStackString() {
        assertThat(Day05.toStackString(), is("NDP"));
    }
}
