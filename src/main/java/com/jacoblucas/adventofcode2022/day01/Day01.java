package com.jacoblucas.adventofcode2022.day01;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Day01 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day01-input.txt");

        final List<Integer> caloriesPerElf = input.stream()
                .map(strs -> strs.stream().mapToInt(Integer::parseInt))
                .map(IntStream::sum)
                .toList();

        // Part 1
        final Optional<Integer> max = caloriesPerElf.stream().max(Comparator.naturalOrder());
        System.out.println(max.get());

        // Part 2
        final int top3 = caloriesPerElf
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::valueOf)
                .sum();
        System.out.println(top3);
    }
}
