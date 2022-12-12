package com.jacoblucas.adventofcode2022.day11;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day11 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day11-input.txt");
        final List<Monkey> monkeys = new ArrayList<>(input.stream()
                .map(Monkey::parse)
                .toList());
        final KeepAway keepAway = new KeepAway(monkeys);

        // Part 1
        IntStream.range(0, 20).forEach(i -> {
            System.out.println("======= ROUND " + (i+1) + " =======");
            keepAway.executeRound();
            System.out.println("After round " + (i+1) + " the monkeys are holding items with these worry levels:");
            monkeys.forEach(m -> System.out.println("Monkey " + m.id() + ": " + (new ArrayList<>(m.items()))));
        });
        System.out.println(keepAway.monkeyBusiness());
    }
}
