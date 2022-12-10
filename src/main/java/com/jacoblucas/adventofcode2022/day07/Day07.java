package com.jacoblucas.adventofcode2022.day07;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Day07 {

    public static Directory parseFileSystem(final List<String> input) {
        Directory root = new Directory("/", null);

        // input(0) is always "cd /", so assume we're starting at root
        Directory current = root;
        for (int i=1; i<input.size(); i++) {
            final String line = input.get(i);
            if (line.startsWith("$ ls")) {
                // do nothing
                continue;
            } else if (line.startsWith("$ cd")) {
                // move directories
                final String targetDirName = line.split(" ")[2];
                if (targetDirName.equals("..")) {
                    current = current.getParent();
                } else {
                    final String currentDirName = current.toString();
                    current = current.getSubDirectories()
                            .stream()
                            .filter(f -> f.getName().equals(targetDirName))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Directory " + targetDirName + " does not exist in " + currentDirName));
                }
            } else {
                // add files
                final String[] parts = line.split(" ");
                if (parts[0].equals("dir")) {
                    current.addFile(new Directory(parts[1], current));
                } else {
                    current.addFile(new DataFile(parts[1], Long.parseLong(parts[0])));
                }
            }
        }

        return root;
    }

    public static List<Directory> findDirectories(final Directory root, final long sizeCeiling) {
        final List<Directory> filtered = new ArrayList<>();
        final Deque<Directory> queue = new ArrayDeque<>(root.getSubDirectories());
        while (!queue.isEmpty()) {
            final Directory dir = queue.pop();
            if (dir.getSize() <= sizeCeiling) {
                filtered.add(dir);
            }
            queue.addAll(dir.getSubDirectories());
        }
        return filtered;
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day07-input.txt");
        final Directory root = parseFileSystem(input);

        root.ls();

        // Part 1
        final List<Directory> directories = findDirectories(root, 100000);
        System.out.println(directories.stream().mapToLong(Directory::getSize).sum());
    }
}
