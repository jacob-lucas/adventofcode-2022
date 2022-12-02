package com.jacoblucas.adventofcode2022.day02;

import org.junit.Test;

import static com.jacoblucas.adventofcode2022.day02.Shape.PAPER;
import static com.jacoblucas.adventofcode2022.day02.Shape.ROCK;
import static com.jacoblucas.adventofcode2022.day02.Shape.SCISSORS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShapeTest {
    @Test
    public void testParse() {
        assertThat(Shape.parse("A"), is(ROCK));
        assertThat(Shape.parse("X"), is(ROCK));
        assertThat(Shape.parse("B"), is(PAPER));
        assertThat(Shape.parse("Y"), is(PAPER));
        assertThat(Shape.parse("C"), is(SCISSORS));
        assertThat(Shape.parse("Z"), is(SCISSORS));
    }

    @Test(expected = IllegalStateException.class)
    public void testParseInvalidValue() {
        Shape.parse("Q");
    }
}
