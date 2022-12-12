package com.jacoblucas.adventofcode2022.day10;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;

public class CRT {
    public static final char LIT = '#';
    public static final char DARK = '.';

    private static final char[][] pixels = new char[6][40];

    // If the sprite is positioned such that one of its three pixels is the
    // pixel currently being drawn, the screen produces a lit pixel (#);
    // otherwise, the screen leaves the pixel dark (.).
    public static void draw(final int cycle, final int spritePosition) {
        int col = (cycle-1) % pixels[0].length;
        int row = (cycle-1) / pixels[0].length;

        if (ImmutableList.of(spritePosition-1, spritePosition, spritePosition+1).contains(col)) {
            pixels[row][col] = LIT;
        } else {
            pixels[row][col] = DARK;
        }
        System.out.println("[tick] pixels[" + row + "][" + col + "] = " + pixels[row][col]);
    }

    public static void display() {
        Arrays.stream(pixels).forEach(System.out::println);
    }
}
