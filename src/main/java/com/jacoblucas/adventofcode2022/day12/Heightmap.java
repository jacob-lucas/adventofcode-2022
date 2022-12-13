package com.jacoblucas.adventofcode2022.day12;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2022.utils.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Heightmap {
    private static final char START = 'S';
    private static final char END = 'E';

    private final char[][] map;

    private final int width, height;

    public Heightmap(final List<String> input) {
        height = input.size();
        width = input.get(0).length();
        map = new char[height][width];
        for (int h = 0; h < height; h++) {
            final String line = input.get(h);
            for (int w = 0; w < width; w++) {
                map[h][w] = line.charAt(w);
            }
        }
    }

    public Pair<Integer, Integer> start() {
        return find(START);
    }

    public Pair<Integer, Integer> end() {
        return find(END);
    }

    private Pair<Integer, Integer> find(final char point) {
        for (int h = 0; h < height; h++) {
            final String line = new String(map[h]);
            if (!line.contains(""+point)) {
                continue;
            }
            for (int w = 0; w < width; w++) {
                if (map[h][w] == point) {
                    return new Pair<>(h, w);
                }
            }
        }
        return null;
    }

    public List<Pair<Integer, Integer>> findPath(final Pair<Integer, Integer> from, final Pair<Integer, Integer> to) {
        // Step, and path so far
        Deque<Pair<Pair<Integer, Integer>, LinkedList<Pair<Integer, Integer>>>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(from, new LinkedList<>(ImmutableList.of(from))));

        final Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.add(from);

        while (!queue.isEmpty()) {
            final Pair<Pair<Integer, Integer>, LinkedList<Pair<Integer, Integer>>> next = queue.pop();

            final Pair<Integer, Integer> curr = next.first();
            final LinkedList<Pair<Integer, Integer>> path = next.second();

            visited.add(curr);

            if (curr.equals(to)) {
                System.out.println("Found path from " + from + " to " + to + " in " + path.size() + " steps.");
                return path;
            } else {
                final List<Pair<Integer, Integer>> steps = validSteps(curr, visited);
                steps.forEach(s -> {
                    final boolean exists = new ArrayList<>(queue).stream()
                            .map(Pair::first)
                            .anyMatch(p -> p.equals(s));
                    if (!exists) {
                        // only explore if a path to this step isn't already queued up
                        final LinkedList<Pair<Integer, Integer>> updatedPath = new LinkedList<>(path);
                        updatedPath.add(s);
                        queue.add(new Pair<>(s, updatedPath));
                    }
                });
            }
        }

        System.out.println("No viable path discovered from " + from + " to " + to + ".");
        return ImmutableList.of();
    }

    // During each step, you can move exactly one square up, down, left, or right. To avoid needing to get out your climbing gear,
    // the elevation of the destination square can be at most one higher than the elevation of your current square; that is,
    // if your current elevation is m, you could step to elevation n, but not to elevation o.
    // (This also means that the elevation of the destination square can be much lower than the elevation of your current square.)
    public List<Pair<Integer, Integer>> validSteps(final Pair<Integer, Integer> from, final Set<Pair<Integer, Integer>> visited) {
        final char c = map[from.first()][from.second()];
        final char current = c == START ? 'a' : c == END ? 'z' : c;
        final List<Pair<Integer, Integer>> validSteps = new ArrayList<>();
        for (int h = -1; h <= 1; h++) {
            for (int w = -1; w <= 1; w++) {
                if (h == 0 || w == 0) {
                    // ignore diagonals
                    final int destWidth = from.second() + w;
                    final int destHeight = from.first() + h;
                    final Pair<Integer, Integer> step = new Pair<>(destHeight, destWidth);
                    if (destHeight >= 0 && destHeight < height && destWidth >= 0 && destWidth < width && !visited.contains(step) && !step.equals(from)) {
                        // on the map and not visited
                        final char d = map[destHeight][destWidth];
                        final char dest = d == START ? 'a' : d == END ? 'z' : d;
                        if (dest - current <= 1) {
                            // valid step up
                            validSteps.add(step);
                        }
                    }
                }
            }
        }
        return validSteps;
    }

    public List<List<Pair<Integer, Integer>>> findShortestPath(final Pair<Integer, Integer> end) {
        // Find all lowest elevations for starting points
        final List<Pair<Integer, Integer>> as = new ArrayList<>();
        for (int h = 0; h < height; h++) {
            final String line = new String(map[h]);
            if (!(line.contains("a") || line.contains(""+START))) {
                continue;
            }
            for (int w = 0; w < width; w++) {
                if (map[h][w] == 'a' || map[h][w] == START) {
                    as.add(new Pair<>(h, w));
                }
            }
        }

        System.out.println("Discovered " + as.size() + " possible starting locations");

        final List<List<Pair<Integer, Integer>>> pathOptions = new ArrayList<>();
        for (final Pair<Integer, Integer> start : as) {
            final List<Pair<Integer, Integer>> path = findPath(start, end);
            if (path.size() > 0) {
                pathOptions.add(path);
            }
        }
        return pathOptions.stream()
                .sorted(Comparator.comparing(List::size))
                .toList();
    }
}
