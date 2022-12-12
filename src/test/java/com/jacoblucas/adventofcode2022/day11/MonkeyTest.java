package com.jacoblucas.adventofcode2022.day11;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.empty;

public class MonkeyTest {
    private List<Monkey> monkeys;
    private KeepAway keepAway;

    @Before
    public void setUp() throws IOException {
        final List<List<String>> testInput = InputReader.readGroups("src/test/resources/","day11-test-input.txt");

        monkeys = testInput.stream()
                .map(Monkey::parse)
                .toList();

        keepAway = new KeepAway(monkeys);
    }

    @Test
    public void testParse() {
        assertThat(monkeys.get(0).id(), is(0));
        assertThat(monkeys.get(0).items(), containsInRelativeOrder(BigInteger.valueOf(79), BigInteger.valueOf(98)));
        assertThat(monkeys.get(0).operation(), is("new = old * 19"));
        assertThat(monkeys.get(0).test().apply(BigInteger.valueOf(23)), is(true));
        assertThat(monkeys.get(0).test().apply(BigInteger.valueOf(24)), is(false));
        assertThat(monkeys.get(0).trueId(), is(2));
        assertThat(monkeys.get(0).falseId(), is(3));
        assertThat(monkeys.get(0).throwTo(BigInteger.valueOf(23)), is(2));
        assertThat(monkeys.get(0).throwTo(BigInteger.valueOf(101)), is(3));
    }

    @Test
    public void testTakeTurn() {
        monkeys.get(0).takeTurn(keepAway);
        assertThat(monkeys.get(0).items(), is(empty()));

        assertThat(monkeys.get(3).items(), containsInRelativeOrder(BigInteger.valueOf(74), BigInteger.valueOf(500), BigInteger.valueOf(620)));
    }

    @Test
    public void testTakeTurnWithOld() {
        monkeys.get(2).takeTurn(keepAway);
        assertThat(monkeys.get(2).items(), is(empty()));

        assertThat(monkeys.get(1).items(), containsInRelativeOrder(BigInteger.valueOf(2080)));
        assertThat(monkeys.get(3).items(), containsInRelativeOrder(BigInteger.valueOf(74), BigInteger.valueOf(1200), BigInteger.valueOf(3136)));
    }
}
