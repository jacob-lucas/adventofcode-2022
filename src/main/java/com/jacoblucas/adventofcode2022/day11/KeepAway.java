package com.jacoblucas.adventofcode2022.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KeepAway {
    private final Map<Integer, Monkey> monkeyMap;

    private final boolean useLCD;

    public KeepAway(final List<Monkey> monkeys, final boolean useLCD) {
        monkeyMap = monkeys.stream()
                .collect(Collectors.toMap(
                        Monkey::id, Function.identity()));
        this.useLCD = useLCD;
    }

    public void executeRound() {
        monkeyMap.keySet().stream().sorted().forEach(i -> {
            final Monkey monkey = monkeyMap.get(i);
            monkey.takeTurn(this, useLCD);
        });
    }

    public Monkey get(int id) {
        return monkeyMap.get(id);
    }

    // Get the LCD of all the monkey test divisors
    public BigInteger lcd() {
        BigInteger result = BigInteger.ONE;
        final List<BigInteger> bigIntegers = monkeyMap.values()
                .stream()
                .map(Monkey::getTestDivisor)
                .toList();
        for (BigInteger bi : bigIntegers) {
            result = result.multiply(bi);
        }
        return result;
    }

    public BigInteger monkeyBusiness() {
        final List<Monkey> monkeys = new ArrayList<>(monkeyMap.values());
        monkeys.sort(Comparator.comparing(Monkey::getInspected));
        monkeys.forEach(m -> System.out.println(m.id() + ": " + m.getInspected()));
        return monkeys.get(monkeys.size()-2).getInspected().multiply(monkeys.get(monkeys.size()-1).getInspected());
    }
}
