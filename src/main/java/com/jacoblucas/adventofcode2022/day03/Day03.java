package com.jacoblucas.adventofcode2022.day03;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day03 {
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
    }
}
