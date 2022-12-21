package com.jacoblucas.adventofcode2022.day21;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day21Test {
    private static final List<String> INPUT = ImmutableList.of(
            "root: pppw + sjmn",
            "dbpl: 5",
            "cczh: sllz + lgvd",
            "zczc: 2",
            "ptdq: humn - dvpt",
            "dvpt: 3",
            "lfqf: 4",
            "humn: 5",
            "ljgn: 2",
            "sjmn: drzm * dbpl",
            "sllz: 4",
            "pppw: cczh / lfqf",
            "lgvd: ljgn * ptdq",
            "drzm: hmdt - zczc",
            "hmdt: 32");

    @Test
    public void testResolve() {
        assertThat(Day21.resolve("root", INPUT), is(152L));
        assertThat(Day21.resolve("pppw", INPUT), is(2L));
        assertThat(Day21.resolve("sjmn", INPUT), is(150L));
    }

    @Test
    public void testSolve() {
        Day21.resolve("root", INPUT);
        assertThat(Day21.solveFor("humn", new ArrayList<>(INPUT)), is(301L));
    }
}
