package com.jacoblucas.adventofcode2022.day09;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day09 {
    public static void simulate(final Bridge bridge, final List<String> instructions) {
        instructions.forEach(str -> {
            final String[] parts = str.split(" ");
            final int n = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "R" -> bridge.move(1, 0, n);
                case "L" -> bridge.move(-1, 0, n);
                case "U" -> bridge.move(0, -1, n);
                case "D" -> bridge.move(0, 1, n);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day09-input.txt");

        Bridge bridge = new Bridge(2);

        // Part 1
        simulate(bridge, input);
        System.out.println(bridge.getTailLocationMap().size());

        // Part 2
        bridge = new Bridge(10);
        simulate(bridge, input);
        System.out.println(bridge.getTailLocationMap().size());
    }
}
