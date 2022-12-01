package com.jacoblucas.adventofcode2022.day01;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 {
    public static IntStream getTopN(final List<Integer> ints, final int n) {
        return ints.stream()
                .sorted(Comparator.reverseOrder())
                .limit(n)
                .mapToInt(Integer::valueOf);
    }

    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day01-input.txt");

        final List<Integer> caloriesPerElf = input.stream()
                .map(strs -> strs.stream().mapToInt(Integer::parseInt))
                .map(IntStream::sum)
                .toList();

        // Part 1
        final int max = getTopN(caloriesPerElf, 1).sum();
        System.out.println(max);

        // Part 2
        final int top3 = getTopN(caloriesPerElf, 3).sum();
        System.out.println(top3);
    }
}
