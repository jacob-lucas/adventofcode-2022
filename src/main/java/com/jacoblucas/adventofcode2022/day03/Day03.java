package com.jacoblucas.adventofcode2022.day03;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day03 {
    public static Item getBadge(final List<Rucksack> group) {
        return Sets.intersection(Sets.intersection(group.get(0).itemSet(), group.get(1).itemSet()), group.get(2).itemSet())
                .stream()
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day03-input.txt");

        final List<Rucksack> rucksacks = input.stream()
                .map(Rucksack::new)
                .toList();

        // Part 1
        int sum = rucksacks.stream()
                .map(Rucksack::getOverlappingItems)
                .mapToInt(is -> is.stream().mapToInt(Item::getPriority).sum())
                .sum();
        System.out.println(sum);

        // Part 2
        final List<List<Rucksack>> groups = Lists.partition(rucksacks, 3);
        sum = groups.stream()
                .map(Day03::getBadge)
                .mapToInt(Item::getPriority)
                .sum();
        System.out.println(sum);
    }
}
