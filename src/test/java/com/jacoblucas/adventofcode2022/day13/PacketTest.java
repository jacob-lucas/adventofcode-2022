package com.jacoblucas.adventofcode2022.day13;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PacketTest {

    protected static final Packet p1 = Packet.parse("[1,1,3,1,1]");
    protected static final Packet p2 = Packet.parse("[1,1,5,1,1]");
    protected static final Packet p3 = Packet.parse("[[1],[2,3,4]]");
    protected static final Packet p4 = Packet.parse("[[1],4]");
    protected static final Packet p5 = Packet.parse("[9]");
    protected static final Packet p6 = Packet.parse("[[8,7,6]]");
    protected static final Packet p7 = Packet.parse("[[4,4],4,4]");
    protected static final Packet p8 = Packet.parse("[[4,4],4,4,4]");
    protected static final Packet p9 = Packet.parse("[7,7,7,7]");
    protected static final Packet p10 = Packet.parse("[7,7,7]");
    protected static final Packet p11 = Packet.parse("[]");
    protected static final Packet p12 = Packet.parse("[3]");
    protected static final Packet p13 = Packet.parse("[[[]]]");
    protected static final Packet p14 = Packet.parse("[[]]");
    protected static final Packet p15 = Packet.parse("[1,[2,[3,[4,[5,6,7]]]],8,9]");
    protected static final Packet p16 = Packet.parse("[1,[2,[3,[4,[5,6,0]]]],8,9]");

    @Test
    public void testExample1() {
        assertThat(p1, is(ImmutableList.of(1,1,3,1,1)));
    }

    @Test
    public void testExample2() {
        assertThat(p2, is(ImmutableList.of(1,1,5,1,1)));
    }

    @Test
    public void testExample3() {
        assertThat(p3, is(ImmutableList.of(
                ImmutableList.of(1),
                ImmutableList.of(2,3,4))));
    }

    @Test
    public void testExample4() {
        assertThat(p4, is(ImmutableList.of(
                ImmutableList.of(1),
                4)));
    }

    @Test
    public void testExample5() {
        assertThat(p5, is(ImmutableList.of(9)));
    }

    @Test
    public void testExample6() {
        assertThat(p6, is(ImmutableList.of(
                ImmutableList.of(8,7,6))));
    }

    @Test
    public void testExample7() {
        assertThat(p7, is(ImmutableList.of(
                ImmutableList.of(4,4),
                4,
                4)));
    }

    @Test
    public void testExample8() {
        assertThat(p8, is(ImmutableList.of(
                ImmutableList.of(4,4),
                4,
                4,
                4)));
    }

    @Test
    public void testExample9() {
        assertThat(p9, is(ImmutableList.of(7,7,7,7)));
    }

    @Test
    public void testExample10() {
        assertThat(p10, is(ImmutableList.of(7,7,7)));
    }

    @Test
    public void testExample11() {
        assertThat(p11, is(ImmutableList.of()));
    }

    @Test
    public void testExample12() {
        assertThat(p12, is(ImmutableList.of(3)));
    }

    @Test
    public void testExample13() {
        assertThat(p13, is(ImmutableList.of(
                ImmutableList.of(
                        ImmutableList.of()
                )
        )));
    }

    @Test
    public void testExample14() {
        assertThat(p14, is(ImmutableList.of(
                ImmutableList.of()
        )));
    }

    @Test
    public void testExample15() {
        assertThat(p15, is(
                ImmutableList.of(
                        1,
                        ImmutableList.of(
                                2,
                                ImmutableList.of(
                                        3,
                                        ImmutableList.of(
                                                4,
                                                ImmutableList.of(5,6,7)
                                        )
                                )
                        ),
                        8,
                        9
                )
        ));
    }

    @Test
    public void testExample16() {
        assertThat(p16, is(
                ImmutableList.of(
                        1,
                        ImmutableList.of(
                                2,
                                ImmutableList.of(
                                        3,
                                        ImmutableList.of(
                                                4,
                                                ImmutableList.of(5,6,0)
                                        )
                                )
                        ),
                        8,
                        9
                )
        ));
    }

    @Test
    public void testCompareTo() {
        assertThat(p1.compareTo(p2), is(-1));
        assertThat(p3.compareTo(p4), is(-1));
        assertThat(p5.compareTo(p6), is(1));
        assertThat(p7.compareTo(p8), is(-1));
        assertThat(p9.compareTo(p10), is(1));
        assertThat(p11.compareTo(p12), is(-1));
        assertThat(p13.compareTo(p14), is(1));
        assertThat(p15.compareTo(p16), is(1));
    }
}
