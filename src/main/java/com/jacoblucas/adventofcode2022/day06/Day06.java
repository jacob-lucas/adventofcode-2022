package com.jacoblucas.adventofcode2022.day06;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day06 {
    public static int detectStartOfPacketMarker(final String buffer, final int len) {
        for (int i=0; i<buffer.length(); i++) {
            final String header = buffer.substring(i, Math.min(i + len, buffer.length()));
            if (header.length() < 4) {
                return -1;
            } else {
                final Set<Integer> headerSet = header.chars().boxed().collect(Collectors.toSet());
                if (headerSet.size() == len) {
                    return i + len;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day06-input.txt");

        // Part 1
        System.out.println(detectStartOfPacketMarker(input.get(0), 4));

        // Part 2
        System.out.println(detectStartOfPacketMarker(input.get(0), 14));
    }
}
