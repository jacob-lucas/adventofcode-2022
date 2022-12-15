package com.jacoblucas.adventofcode2022.day11;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day11 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day11-input.txt");

        // Part 1
        List<Monkey> monkeys = new ArrayList<>(input.stream()
                .map(Monkey::parse)
                .toList());
        final KeepAway keepAway1 = new KeepAway(monkeys, false);
        IntStream.range(0, 20).forEach(i -> keepAway1.executeRound());
        System.out.println(keepAway1.monkeyBusiness());

        // Part 2
        monkeys = new ArrayList<>(input.stream()
                .map(Monkey::parse)
                .toList());
        final KeepAway keepAway2 = new KeepAway(monkeys, true);
        IntStream.range(0, 10000).forEach(i -> keepAway2.executeRound());
        System.out.println(keepAway2.monkeyBusiness());
    }
}
