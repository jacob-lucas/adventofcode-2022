package com.jacoblucas.adventofcode2022.day09;

import com.jacoblucas.adventofcode2022.utils.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BridgeTest {
    private Bridge bridge;

    @Before
    public void setUp() {
        bridge = new Bridge(2);
        bridge.knots[0][0] = 0;
        bridge.knots[0][1] = 4;
        bridge.knots[1][0] = 0;
        bridge.knots[1][1] = 4;
    }

    @Test
    public void testHead() {
        assertThat(bridge.head(), is(new Pair<>(0,4)));
    }

    @Test
    public void testTail() {
        assertThat(bridge.tail(), is(new Pair<>(0,4)));
    }

    @Test
    public void testMoveRight() {
        bridge.move(1, 0, 4);
        assertThat(bridge.head(), is(new Pair<>(4,4)));
        assertThat(bridge.tail(), is(new Pair<>(3,4)));
    }

    @Test
    public void testMoveUp() {
        bridge.move(0, -1, 4);
        assertThat(bridge.head(), is(new Pair<>(0,0)));
        assertThat(bridge.tail(), is(new Pair<>(0,1)));
    }

    @Test
    public void testMoveLeft() {
        bridge.move(-1, 0, 3);
        assertThat(bridge.head(), is(new Pair<>(-3,4)));
        assertThat(bridge.tail(), is(new Pair<>(-2,4)));
    }

    @Test
    public void testMoveDown() {
        bridge.move(0, 1, 2);
        assertThat(bridge.head(), is(new Pair<>(0,6)));
        assertThat(bridge.tail(), is(new Pair<>(0,5)));
    }

    @Test
    public void testUpRight() {
        bridge = new Bridge(2);
        bridge.knots[0][0] = 4;
        bridge.knots[0][1] = -1;
        bridge.knots[1][0] = 3;
        bridge.knots[1][1] = 0;
        bridge.move(0, -1, 1);
        assertThat(bridge.head(), is(new Pair<>(4,-2)));
        assertThat(bridge.tail(), is(new Pair<>(4,-1)));
    }

    @Test
    public void testMoveCombo() {
        // R 4
        bridge.move(1, 0, 4);
        assertThat(bridge.head(), is(new Pair<>(4,4)));
        assertThat(bridge.tail(), is(new Pair<>(3,4)));

        // U 4
        bridge.move(0, -1, 4);
        assertThat(bridge.head(), is(new Pair<>(4,0)));
        assertThat(bridge.tail(), is(new Pair<>(4,1)));

        // L 3
        bridge.move(-1, 0, 3);
        assertThat(bridge.head(), is(new Pair<>(1,0)));
        assertThat(bridge.tail(), is(new Pair<>(2,0)));

        // D 1
        bridge.move(0, 1, 1);
        assertThat(bridge.head(), is(new Pair<>(1,1)));
        assertThat(bridge.tail(), is(new Pair<>(2,0)));
    }
}
