package com.jacoblucas.adventofcode2022.day07;

public record DataFile(String name, long size) implements File {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }
}
