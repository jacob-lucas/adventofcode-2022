package com.jacoblucas.adventofcode2022.day03;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day03Test {
    @Test
    public void testGetBadge() {
        Rucksack r1 = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
        Rucksack r2 = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        Rucksack r3 = new Rucksack("PmmdzqPrVvPwwTWBwg");

        assertThat(Day03.getBadge(ImmutableList.of(r1, r2, r3)), is(new Item('r')));
    }
}
