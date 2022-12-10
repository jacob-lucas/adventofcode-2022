package com.jacoblucas.adventofcode2022.day08;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day08 {

    public static Tree[][] parse(final List<String> input) {
        final Tree[][] result = new Tree[input.size()][input.get(0).length()];
        for (int i=0; i<input.size(); i++) {
            final String line = input.get(i);
            result[i] = new Tree[line.length()];
            for (int j=0; j<line.length(); j++) {
                result[i][j] = new Tree(Integer.parseInt(""+line.charAt(j)));
            }
        }
        return result;
    }

    // 0,0 is top left
    public static boolean isVisible(final Tree[][] map, int x, int y) {
        if (x < 0 || x >= map[0].length) {
            return false;
        } else if (y < 0 || y > map.length) {
            return false;
        } else if (x == 0 || x == map[0].length - 1 || y == 0 || y == map.length - 1) {
            return true;
        } else {
            return isVisible(map, x, y, map[y][x].height(), -1, 0) ||
                    isVisible(map, x, y, map[y][x].height(), 1, 0) ||
                    isVisible(map, x, y, map[y][x].height(), 0, 1) ||
                    isVisible(map, x, y, map[y][x].height(), 0, -1);
        }
    }

    protected static boolean isVisible(final Tree[][] map, int x, int y, int refHeight, int dx, int dy) {
        int coordX = x + dx;
        int coordY = y + dy;
        if (coordX < 0 || coordX >= map[0].length || coordY < 0 || coordY >= map.length) {
            return true;
        }
        if (map[coordY][coordX].height() >= refHeight) {
            return false;
        }

        return isVisible(map, coordX, coordY, refHeight, dx, dy);
    }

    public static int countVisible(final Tree[][] map) {
        int visible = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (isVisible(map, x, y)) {
                    visible++;
                }
            }
        }
        return visible;
    }

    public static long scenicScore(final Tree[][] map, int x, int y) {
        return scenicScore(map, x, y, map[y][x].height(), -1, 0, 0) *
                scenicScore(map, x, y, map[y][x].height(), 1, 0, 0) *
                scenicScore(map, x, y, map[y][x].height(), 0, -1, 0) *
                scenicScore(map, x, y, map[y][x].height(), 0, 1, 0);
    }

    protected static long scenicScore(final Tree[][] map, int x, int y, int refHeight, int dx, int dy, long count) {
        int coordX = x + dx;
        int coordY = y + dy;

        if (coordX < 0 || coordX >= map[0].length || coordY < 0 || coordY >= map.length) {
            return count;
        }

        if (map[coordY][coordX].height() >= refHeight) {
            return count + 1;
        }

        return scenicScore(map, coordX, coordY, refHeight, dx, dy, count + 1);
    }

    public static long maxScenicScore(final Tree[][] map) {
        long maxScore = Long.MIN_VALUE;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                long scenicScore = scenicScore(map, x, y);
                if (scenicScore > maxScore) {
                    maxScore = scenicScore;
                }
            }
        }
        return maxScore;
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day08-input.txt");

        final Tree[][] map = parse(input);

        // Part 1
        System.out.println(countVisible(map));

        // Part 2
        System.out.println(maxScenicScore(map));
    }
}
