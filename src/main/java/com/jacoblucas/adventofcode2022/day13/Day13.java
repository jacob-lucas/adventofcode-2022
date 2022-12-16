package com.jacoblucas.adventofcode2022.day13;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Day13 {

    public static int getOrderedPairIndexSum(final List<Pair<Packet, Packet>> packetPairs) {
        return IntStream.range(0, packetPairs.size())
                .map(i -> {
                    final Pair<Packet, Packet> pair = packetPairs.get(i);
                    if (pair.first().compareTo(pair.second()) < 0) {
                        System.out.println("Pair " + (i+1) + " is in the right order");
                        return i + 1;
                    } else {
                        return 0;
                    }
                })
                .sum();
    }

    public static void main(String[] args) throws IOException {
        final List<List<String>> input = InputReader.readGroups("day13-input.txt");

        final List<Pair<Packet, Packet>> packetPairs = input.stream()
                .map(ps -> ps.stream()
                        .map(Packet::parse)
                        .toList())
                .map(ls -> new Pair<>(ls.get(0), ls.get(1)))
                .toList();

        // Part 1
        final int sumOfIndices = getOrderedPairIndexSum(packetPairs);
        System.out.println(sumOfIndices);
    }
}
