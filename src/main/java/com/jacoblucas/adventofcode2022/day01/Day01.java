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

        final Optional<Integer> max = input.stream()
                .map(strs -> strs.stream().mapToInt(Integer::parseInt))
                .map(IntStream::sum)
                .max(Comparator.naturalOrder());

        System.out.println(max.get());
    }
}
