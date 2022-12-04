package com.jacoblucas.adventofcode2022.day04;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Day04 {
    public static List<Pair<List<Integer>, List<Integer>>> parseInput(final List<String> input) {
        return input.stream()
                .map(str -> str.split(","))
                .map(strs -> {
                    final String[] s0 = strs[0].split("-");
                    final String[] s1 = strs[1].split("-");
                    return new Pair<>(
                            IntStream.range(Integer.parseInt(s0[0]), Integer.parseInt(s0[1]) + 1)
                                    .boxed()
                                    .toList(),
                            IntStream.range(Integer.parseInt(s1[0]), Integer.parseInt(s1[1]) + 1)
                                    .boxed()
                                    .toList());
                })
                .toList();
    }

    public static boolean isContainedBy(final List<Integer> left, final List<Integer> right) {
        return left.get(0) <= right.get(0) && left.get(left.size()-1) >= right.get(right.size()-1);
    }

    public static boolean overlaps(final List<Integer> left, final List<Integer> right) {
        final List<Integer> smaller = left.get(0) < right.get(0) ? left : right;
        final List<Integer> larger = left.get(0) < right.get(0) ? right : left;
        return smaller.get(smaller.size()-1) >= larger.get(0);
    }

    public static long countFullyContained(final List<Pair<List<Integer>, List<Integer>>> pairs) {
        return pairs.stream()
                .filter(p -> isContainedBy(p.first(), p.second()) || isContainedBy(p.second(), p.first()))
                .count();
    }

    public static long countOverlaps(final List<Pair<List<Integer>, List<Integer>>> pairs) {
        return pairs.stream()
                .filter(p -> Day04.overlaps(p.first(), p.second()))
                .count();
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day04-input.txt");
        final List<Pair<List<Integer>, List<Integer>>> assignmentPairs = parseInput(input);

        // Part 1
        long fullyContainedAssignmentPairs = countFullyContained(assignmentPairs);
        System.out.println(fullyContainedAssignmentPairs);

        // Part 2
        long overlaps = countOverlaps(assignmentPairs);
        System.out.println(overlaps);
    }
}
