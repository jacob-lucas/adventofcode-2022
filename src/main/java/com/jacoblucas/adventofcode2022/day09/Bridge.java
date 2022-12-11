package com.jacoblucas.adventofcode2022.day09;

import com.jacoblucas.adventofcode2022.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Bridge {
    private final Pair<Integer, Integer> start;

    private int headX, headY, tailX, tailY;

    private final Map<Pair<Integer, Integer>, Integer> tailLocationMap;

    public Bridge(int headX, int headY, int tailX, int tailY) {
        this.headX = headX;
        this.headY = headY;
        this.tailX = tailX;
        this.tailY = tailY;
        start = new Pair<>(headX, headY);
        tailLocationMap = new HashMap<>();
        tailLocationMap.put(new Pair<>(tailX, tailY), 1);
    }

    public Pair<Integer, Integer> start() {
        return start;
    }

    public Pair<Integer, Integer> head() {
        return new Pair<>(headX, headY);
    }

    public Pair<Integer, Integer> tail() {
        return new Pair<>(tailX, tailY);
    }

    public Map<Pair<Integer, Integer>, Integer> getTailLocationMap() {
        return tailLocationMap;
    }

    public void move(int dx, int dy, int n) {
        IntStream.range(0, n).forEach(i -> {
            // move the head
            headX += dx;
            headY += dy;
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
        // Assumes the head has moved, check to see if the tail moves with it
        int dx = headX - tailX;
        int dy = headY - tailY;
        final int absDx = Math.abs(dx);
        final int absDy = Math.abs(dy);
        boolean moved = false;
        if ((absDx == touchingDistance && dy == 0)) {
            // move left/right
            tailX += (dx/absDx);
            moved = true;
        } else if (absDy == touchingDistance && dx == 0) {
            // move up/down
            tailY += (dy/absDy);
            moved = true;
        } else if (absDx > 0 && absDy > 0 && (absDx == touchingDistance || absDy == touchingDistance)) {
            // move diagonally
            tailX += (dx/absDx);
            tailY += (dy/absDy);
            moved = true;
        }

        if (moved) {
            final Pair<Integer, Integer> tailLocation = new Pair<>(tailX, tailY);
            tailLocationMap.put(tailLocation, tailLocationMap.getOrDefault(tailLocation, 0) + 1);
        }
    }
}
