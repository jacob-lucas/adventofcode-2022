package com.jacoblucas.adventofcode2022.day10;

public record NoopInstruction(String name, int value, int cyclesToComplete) implements Instruction {}
