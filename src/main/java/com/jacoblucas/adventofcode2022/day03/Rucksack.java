package com.jacoblucas.adventofcode2022.day03;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Rucksack(String items) {
    public String getCompartment1() {
        return items.substring(0, items.length() / 2);
    }

    public String getCompartment2() {
        return items.substring(items.length() / 2);
    }

    public Set<Item> itemSet() {
        return toItemSet(items);
    }

    public List<Item> getOverlappingItems() {
        final Set<Item> c1 = toItemSet(getCompartment1());
        final Set<Item> c2 = toItemSet(getCompartment2());
        return Sets.intersection(c1, c2)
                .stream()
                .toList();
    }

    private Set<Item> toItemSet(final String str) {
        return str.chars()
                .mapToObj(ch -> new Item((char)ch))
                .collect(Collectors.toSet());
    }
}
