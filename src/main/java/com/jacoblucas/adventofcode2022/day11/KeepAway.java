package com.jacoblucas.adventofcode2022.day11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KeepAway {
    private final Map<Integer, Monkey> monkeyMap;

    public KeepAway(final List<Monkey> monkeys) {
        monkeyMap = monkeys.stream()
                .collect(Collectors.toMap(
                        Monkey::id, Function.identity()));
    }

    public void executeRound() {
        monkeyMap.keySet().stream().sorted().forEach(i -> {
            final Monkey monkey = monkeyMap.get(i);
            monkey.takeTurn(this);
        });
    }

    public Monkey get(int id) {
        return monkeyMap.get(id);
    }

    public int monkeyBusiness() {
        final List<Monkey> monkeys = new ArrayList<>(monkeyMap.values());
        monkeys.sort(Comparator.comparing(Monkey::getInspected));
        monkeys.forEach(m -> System.out.println(m.id() + ": " + m.getInspected()));
        return monkeys.get(monkeys.size()-2).getInspected() * monkeys.get(monkeys.size()-1).getInspected();
    }
}
