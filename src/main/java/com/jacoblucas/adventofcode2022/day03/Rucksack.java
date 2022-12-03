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

    public List<Item> getOverlappingItems() {
        final Set<Item> c1 = getCompartment1().chars()
                .mapToObj(ch -> new Item((char)ch))
                .collect(Collectors.toSet());
        final Set<Item> c2 = getCompartment2().chars()
                .mapToObj(ch -> new Item((char)ch))
                .collect(Collectors.toSet());

        return Sets.intersection(c1, c2).stream().toList();
    }
}
