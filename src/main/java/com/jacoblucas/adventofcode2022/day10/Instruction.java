package com.jacoblucas.adventofcode2022.day10;

public interface Instruction {
    String name();

    int value();

    int cyclesToComplete();
}
