package com.jacoblucas.adventofcode2022.day05;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day05Test {
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
        final List<String> stackInput = ImmutableList.of(
                "    [D]",
                "[N] [C]",
                "[Z] [M] [P]",
                " 1   2   3");
        final Map<Integer, Stack<Character>> stacks = Day05.parseStacks(stackInput);
        final List<String> instructionInput = ImmutableList.of(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2");

        Day05.execute(instructionInput.stream().map(Instruction::parse).toList(), stacks);

        final Stack<Character> expectedS1 = new Stack<>();
        expectedS1.push('C');

        final Stack<Character> expectedS2 = new Stack<>();
        expectedS2.push('M');

        final Stack<Character> expectedS3 = new Stack<>();
        expectedS3.push('P');
        expectedS3.push('D');
        expectedS3.push('N');
        expectedS3.push('Z');

        assertThat(stacks.get(1), is(expectedS1));
        assertThat(stacks.get(2), is(expectedS2));
        assertThat(stacks.get(3), is(expectedS3));
    }

    @Test
    public void testExecuteCrateMover9001() {
        final List<String> stackInput = ImmutableList.of(
                "    [D]",
                "[N] [C]",
                "[Z] [M] [P]",
                " 1   2   3");
        final Map<Integer, Stack<Character>> stacks = Day05.parseStacks(stackInput);
        final List<String> instructionInput = ImmutableList.of(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2");

        Day05.executeCrateMover9001(instructionInput.stream().map(Instruction::parse).toList(), stacks);

        final Stack<Character> expectedS1 = new Stack<>();
        expectedS1.push('M');

        final Stack<Character> expectedS2 = new Stack<>();
        expectedS2.push('C');

        final Stack<Character> expectedS3 = new Stack<>();
        expectedS3.push('P');
        expectedS3.push('Z');
        expectedS3.push('N');
        expectedS3.push('D');

        assertThat(stacks.get(1), is(expectedS1));
        assertThat(stacks.get(2), is(expectedS2));
        assertThat(stacks.get(3), is(expectedS3));

    }
}
