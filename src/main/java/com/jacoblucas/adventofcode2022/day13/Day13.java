package com.jacoblucas.adventofcode2022.day13;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day13 {
    public static final Packet D1 = Packet.parse("[[2]]");
    public static final Packet D2 = Packet.parse("[[6]]");

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

    public static List<Packet> sort(final List<Pair<Packet, Packet>> packetPairs) {
        final ArrayList<Pair<Packet, Packet>> arrayList = new ArrayList<>(packetPairs);
        arrayList.add(new Pair<>(D1, D2));
        return arrayList.stream()
                .map(p -> ImmutableList.of(p.first(), p.second()))
                .flatMap(List::stream)
                .sorted(Packet::compareTo)
                .toList();
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

        // Part 2
        final List<Packet> sortedPackets = sort(packetPairs);
        final int index1 = sortedPackets.indexOf(D1);
        final int index2 = sortedPackets.indexOf(D2);
        System.out.println((index1+1) * (index2+1));
    }
}
