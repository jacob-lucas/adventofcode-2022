package com.jacoblucas.adventofcode2022.day03;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemTest {
    @Test
    public void testGetPriority() {
        assertThat(new Item('a').getPriority(), is(1));
        assertThat(new Item('z').getPriority(), is(26));
        assertThat(new Item('A').getPriority(), is(27));
        assertThat(new Item('Z').getPriority(), is(52));
    }
}
