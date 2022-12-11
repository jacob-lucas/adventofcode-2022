package com.jacoblucas.adventofcode2022.day09;

import com.jacoblucas.adventofcode2022.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Bridge {
    // n x 2 for number of knots, with x,y pos
    int[][] knots;

    private final Map<Pair<Integer, Integer>, Integer> tailLocationMap;

    public Bridge(int knots) {
        this.knots = new int[knots][2];
        IntStream.range(0, knots).forEach(i -> {
            this.knots[i][0] = 0;
            this.knots[i][1] = 1;
        });
        tailLocationMap = new HashMap<>();
        tailLocationMap.put(new Pair<>(0, 0), 1);
    }

    public Pair<Integer, Integer> head() {
        return new Pair<>(knots[0][0], knots[0][1]);
    }

    public Pair<Integer, Integer> tail() {
        return new Pair<>(knots[knots.length-1][0], knots[knots.length-1][1]);
    }

    public Map<Pair<Integer, Integer>, Integer> getTailLocationMap() {
        return tailLocationMap;
    }

    public void move(int dx, int dy, int n) {
        IntStream.range(0, n).forEach(i -> {
            // move the head
            knots[0][0] += dx;
            knots[0][1] += dy;
            updateTail();
        });
    }

    // the head (H) and tail (T) must always be touching
    // (diagonally adjacent and even overlapping both count as touching)
    //
    // If the head is ever two steps directly up, down, left, or right from the tail,
    // the tail must also move one step in that direction so it remains close enough
    //
    // Otherwise, if the head and tail aren't touching and aren't in the same row or column,
    // the tail always moves one step diagonally to keep up:
    private void updateTail() {
        int touchingDistance = 2;

        final Pair<Integer, Integer> prevTail = tail();

        IntStream.range(1, knots.length).forEach(knot -> {
            // Assumes the head has moved, check to see if the tail moves with it
            int dx = knots[knot-1][0] - knots[knot][0];
            int dy = knots[knot-1][1] - knots[knot][1];
            final int absDx = Math.abs(dx);
            final int absDy = Math.abs(dy);
            if ((absDx == touchingDistance && dy == 0)) {
                // move left/right
                knots[knot][0] += (dx/absDx);
            } else if (absDy == touchingDistance && dx == 0) {
                // move up/down
                knots[knot][1] += (dy/absDy);
            } else if (absDx > 0 && absDy > 0 && (absDx == touchingDistance || absDy == touchingDistance)) {
                // move diagonally
                knots[knot][0] += (dx/absDx);
                knots[knot][1] += (dy/absDy);
            }
        });

        final Pair<Integer, Integer> tailLocation = tail();
        if (!tailLocation.equals(prevTail)) {
            tailLocationMap.put(tailLocation, tailLocationMap.getOrDefault(tailLocation, 0) + 1);
        }
    }
}
