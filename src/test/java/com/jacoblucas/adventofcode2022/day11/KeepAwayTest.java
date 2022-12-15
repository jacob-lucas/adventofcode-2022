package com.jacoblucas.adventofcode2022.day11;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.empty;

public class KeepAwayTest {
    private KeepAway keepAway;
    private List<Monkey> monkeys;

    @Before
    public void setUp() throws IOException {
        final List<List<String>> testInput = InputReader.readGroups("src/test/resources/","day11-test-input.txt");

        monkeys = testInput.stream()
                .map(Monkey::parse)
                .toList();

        keepAway = new KeepAway(monkeys, false);
    }

    @Test
    public void testExecuteRound() {
        keepAway.executeRound();

        assertThat(monkeys.get(0).items(), containsInRelativeOrder(BigInteger.valueOf(20), BigInteger.valueOf(23), BigInteger.valueOf(27), BigInteger.valueOf(26)));
        assertThat(monkeys.get(1).items(), containsInRelativeOrder(BigInteger.valueOf(2080), BigInteger.valueOf(25), BigInteger.valueOf(167), BigInteger.valueOf(207), BigInteger.valueOf(401), BigInteger.valueOf(1046)));
        assertThat(monkeys.get(2).items(), is(empty()));
        assertThat(monkeys.get(3).items(), is(empty()));
    }

    @Test
    public void testTwentyRounds() {
        IntStream.range(0, 20).forEach(i -> keepAway.executeRound());

        assertThat(monkeys.get(0).getInspected().intValue(), is(101));
        assertThat(monkeys.get(1).getInspected().intValue(), is(95));
        assertThat(monkeys.get(2).getInspected().intValue(), is(7));
        assertThat(monkeys.get(3).getInspected().intValue(), is(105));

        assertThat(keepAway.monkeyBusiness().intValue(), is(10605));
    }
}
