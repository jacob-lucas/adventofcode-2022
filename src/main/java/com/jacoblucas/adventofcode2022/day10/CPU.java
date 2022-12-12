package com.jacoblucas.adventofcode2022.day10;

import com.google.common.collect.ImmutableList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CPU {
    private static final List<Integer> INTERESTING_SIGNALS = ImmutableList.of(20,60,100,140,180,220);

    private final Map<String, Integer> registers;

    private final Map<Integer, List<Instruction>> cycleInstructions;

    private int cycle;

    private boolean executionInProgress;

    private int signalStrength;

    public CPU() {
        this.registers = new HashMap<>();
        registers.put("X", 1);

        this.cycleInstructions = new HashMap<>();

        this.cycle = 0;

        this.executionInProgress = false;

        this.signalStrength = 0;
    }

    public int get(final String register) {
        return registers.getOrDefault(register, -1);
    }

    public int getCycle() {
        return cycle;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void execute(final List<Instruction> instructions) {
        final Deque<Instruction> queue = new ArrayDeque<>(instructions);
        while (!queue.isEmpty() || executionInProgress) {
            final Instruction instruction = executionInProgress ? null : queue.pop();
            pre(instruction);
            tick(instruction);
            post(instruction);
        }
    }

    void pre(final Instruction instruction) {
        cycle++;
        System.out.println("[pre ] Cycle #" + (cycle) + " starting...");

        if (instruction != null) {
            final int key = (cycle - 1) + instruction.cyclesToComplete();
            System.out.println("[pre ] Starting " + instruction + " - expected to complete after cycle " + key);

            // seed the instruction for execution completion
            final List<Instruction> instructions = cycleInstructions.getOrDefault(key, new ArrayList<>());
            instructions.add(instruction);
            cycleInstructions.put(key, instructions);
            executionInProgress = true;
        }
    }

    void tick(final Instruction instruction) {
        final int value = registers.get("X");
        System.out.println("[tick] X=" + value);
        if (INTERESTING_SIGNALS.contains(cycle)) {
            signalStrength += cycle * value;
        }
    }

    void post(final Instruction instruction) {
        // apply any instructions that are completing
        final List<Instruction> toExecute = cycleInstructions.getOrDefault(cycle, ImmutableList.of());
        toExecute.forEach(i -> {
            System.out.println("[post] Completing " + i);
            switch (i) {
                case AddxInstruction a -> registers.put("X", registers.get("X") + a.value());
                case NoopInstruction n -> {} // do nothing
                default -> {
                }
            }
        });

        if (!toExecute.isEmpty()) {
            executionInProgress = false;
        }
        System.out.println("[post] Cycle #" + (cycle) + " complete.");
    }
}
