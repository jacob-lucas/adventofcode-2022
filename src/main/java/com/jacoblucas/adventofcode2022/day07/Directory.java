package com.jacoblucas.adventofcode2022.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Directory implements File {
    private final String name;

    private final Directory parent;

    private final List<File> files;

    public Directory(final String name, final Directory parent) {
        this.name = name;
        this.parent = parent;
        this.files = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Directory> getSubDirectories() {
        return files.stream()
                .filter(f -> f instanceof Directory)
                .map(f -> (Directory) f)
                .toList();
    }

    @Override
    public long getSize() {
        return getFiles().stream()
                .mapToLong(File::getSize)
                .sum();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public void addFile(final File... files) {
        this.files.addAll(Arrays.asList(files));
    }

    public String toString() {
        final Stack<String> hierarchy = new Stack<>();
        Directory dir = this;
        while (dir != null) {
            hierarchy.push(dir.getName());
            dir = dir.getParent();
        }
        final StringBuilder sb = new StringBuilder();
        while (!hierarchy.isEmpty()) {
            sb.append(hierarchy.pop());
            sb.append("/");
        }
        final String str = sb.toString();
        return str.substring(1, str.length() - 1); // remove the / at the start & end
    }

    public void ls() {
        ls(0);
    }

    private void ls(final int indent) {
        final StringBuilder sb = new StringBuilder();
        IntStream.range(0, indent).forEach(i -> sb.append(" "));
        sb.append("- " + name + " (dir)");
        System.out.println(sb);

        getFiles().forEach(f -> {
            switch (f) {
                case Directory d -> d.ls(indent + 2);
                case DataFile df -> {
                    final StringBuilder sb1 = new StringBuilder();
                    IntStream.range(0, indent).forEach(i -> sb1.append(" "));
                    sb1.append("  - " + df.getName() + " (file, size=" + df.getSize() + ")");
                    System.out.println(sb1);
                }
                default -> throw new IllegalStateException("Unexpected value: " + f);
            }
        });
    }
}
