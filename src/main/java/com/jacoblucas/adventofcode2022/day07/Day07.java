package com.jacoblucas.adventofcode2022.day07;

import com.jacoblucas.adventofcode2022.utils.InputReader;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

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

    public static List<Directory> findDirectories(final Directory root, final Function<Directory, Boolean> filter) {
        final List<Directory> filtered = new ArrayList<>();
        final Deque<Directory> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final Directory dir = queue.pop();
            if (filter.apply(dir)) {
                filtered.add(dir);
            }
            queue.addAll(dir.getSubDirectories());
        }
        return filtered;
    }

    public static List<Directory> getDeleteCandidates(final long capacity, final long updateSize, final Directory root) {
        final long available = capacity - root.getSize();
        return findDirectories(root, (directory -> directory.getSize() >= updateSize - available));
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day07-input.txt");
        final Directory root = parseFileSystem(input);

        root.ls();

        // Part 1
        List<Directory> directories = findDirectories(root, (directory -> directory.getSize() <= 100000));
        System.out.println(directories.stream().mapToLong(Directory::getSize).sum());

        // Part 2
        directories = getDeleteCandidates(70000000, 30000000, root);
        final Directory toDelete = directories.stream()
                .min(Comparator.comparingLong(Directory::getSize))
                .orElseThrow(() -> new IllegalStateException("no delete candidate available"));
        System.out.println(toDelete.getSize());
    }
}
