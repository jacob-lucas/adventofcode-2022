package com.jacoblucas.adventofcode2022.day25;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day25 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day25-input.txt");

        // Part 1
        long sum = input.stream()
                .mapToLong(SnafuConverter::snafuToDec)
                .sum();
        final String snafuSum = SnafuConverter.decToSnafu(sum);
        System.out.println(snafuSum);
    }
}
