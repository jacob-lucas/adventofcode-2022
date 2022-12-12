package com.jacoblucas.adventofcode2022.day11;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public class Monkey {
    private final int id, trueId, falseId;

    private final Deque<BigInteger> items;

    private final String operation;

    private final Function<BigInteger, Boolean> test;

    private int inspected;

    /**
     * Monkey 0:
     *   Starting items: 79, 98
     *   Operation: new = old * 19
     *   Test: divisible by 23
     *     If true: throw to monkey 2
     *     If false: throw to monkey 3
     */
    public static Monkey parse(final List<String> input) {
        final int id = Integer.parseInt(input.get(0).split(" ")[1].replaceAll(":",""));
        final List<BigInteger> items = Arrays.stream(input.get(1).split(":")[1].substring(1).split(","))
                .map(String::trim)
                .map(BigInteger::new)
                .toList();
        final String operation = input.get(2).split(":")[1].trim();
        final int test = Integer.parseInt(input.get(3).split(" ")[5]);
        final int trueId = Integer.parseInt(input.get(4).split(" ")[9]);
        final int falseId = Integer.parseInt(input.get(5).split(" ")[9]);

        return new Monkey(id, items, operation, test, trueId, falseId);
    }

    public Monkey(final int id, final List<BigInteger> items, final String operation, final int test, final int trueId, final int falseId) {
        this.id = id;
        this.items = new ArrayDeque<>(items);
        this.operation = operation;
        this.test = i -> i.mod(BigInteger.valueOf(test)).equals(BigInteger.ZERO);
        this.trueId = trueId;
        this.falseId = falseId;
        this.inspected = 0;
    }

    public int id() {
        return id;
    }

    public Deque<BigInteger> items() {
        return items;
    }

    public String operation() {
        return operation;
    }

    public Function<BigInteger, Boolean> test() {
        return test;
    }

    public int trueId() {
        return trueId;
    }

    public int falseId() {
        return falseId;
    }

    public int getInspected() {
        return inspected;
    }

    public int throwTo(final BigInteger item) {
        return test.apply(item) ? trueId : falseId;
    }

    // On a single monkey's turn, it inspects and throws all of the items
    // it is holding one at a time and in the order listed.
    public void receive(BigInteger item) {
        items.add(item);
    }

    public void takeTurn(KeepAway keepAway) {
        System.out.println("Monkey " + id + ":");
        while (!items.isEmpty()) {
            BigInteger item = items.pop();
            System.out.println("\tMonkey inspects an item with a worry level of " + item);

            // 1. Inspect, adjust worry level
            final String[] parts = operation().split(" ");
            final String op = parts[3];
            final String valueStr = parts[4];
            final BigInteger value = valueStr.equals("old") ? item : new BigInteger(valueStr);

            BigInteger prev = item;
            item = switch (op) {
                case "+" -> item.add(value);
                case "*" -> item.multiply(value);
                case "-" -> item.subtract(value);
                case "/" -> item.divide(value);
                default -> item;
            };

            System.out.println("\t\tWorry level: " + prev + " " + op + " " + value + " = " + item);

            inspected++;

            // 2. Worry relief
            item = item.divide(BigInteger.valueOf(3));
            System.out.println("\t\tWorry level divided by 3 to " + item);

            // 3. Test new worry level
            int destId = throwTo(item);
            keepAway.get(destId).receive(item);
            System.out.println("\t\tItem with worry level " + item + " is thrown to monkey " + destId);
        }
    }
}
