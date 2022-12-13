package com.jacoblucas.adventofcode2022.day12;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.io.IOException;
import java.util.List;

public class Day12 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day12-input.txt");
        final Heightmap heightmap = new Heightmap(input);

        // Part 1
        final List<Pair<Integer, Integer>> path = heightmap.findPath(heightmap.start(), heightmap.end());
        System.out.println(path.size() - 1);
    }
}
