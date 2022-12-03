package com.jacoblucas.adventofcode2022.day03;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RucksackTest {
    @Test
    public void testRucksack1() {
        Rucksack r = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
        assertThat(r.getCompartment1(), is("vJrwpWtwJgWr"));
        assertThat(r.getCompartment2(), is("hcsFMMfFFhFp"));
    }

    @Test
    public void testRucksack2() {
        Rucksack r = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        assertThat(r.getCompartment1(), is("jqHRNqRjqzjGDLGL"));
        assertThat(r.getCompartment2(), is("rsFMfFZSrLrFZsSL"));
    }

    @Test
    public void testRucksack3() {
        Rucksack r = new Rucksack("PmmdzqPrVvPwwTWBwg");
        assertThat(r.getCompartment1(), is("PmmdzqPrV"));
        assertThat(r.getCompartment2(), is("vPwwTWBwg"));
    }

    @Test
    public void testRucksack4() {
        Rucksack r = new Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
        assertThat(r.getCompartment1(), is("wMqvLMZHhHMvwLH"));
        assertThat(r.getCompartment2(), is("jbvcjnnSBnvTQFn"));
    }

    @Test
    public void testRucksack5() {
        Rucksack r = new Rucksack("ttgJtRGJQctTZtZT");
        assertThat(r.getCompartment1(), is("ttgJtRGJ"));
        assertThat(r.getCompartment2(), is("QctTZtZT"));
    }

    @Test
    public void testRucksack6() {
        Rucksack r = new Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw");
        assertThat(r.getCompartment1(), is("CrZsJsPPZsGz"));
        assertThat(r.getCompartment2(), is("wwsLwLmpwMDw"));
    }

    @Test
    public void testGetOverlappingItems() {
        Rucksack r = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
        assertThat(r.getOverlappingItems(), is(ImmutableList.of(new Item('p'))));

        r = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        assertThat(r.getOverlappingItems(), is(ImmutableList.of(new Item('L'))));

        r = new Rucksack("PmmdzqPrVvPwwTWBwg");
        assertThat(r.getOverlappingItems(), is(ImmutableList.of(new Item('P'))));
    }
}
