package com.jacoblucas.adventofcode2022.day13;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2022.utils.Pair;
import org.junit.Test;

import java.util.List;

import static com.jacoblucas.adventofcode2022.day13.PacketTest.p1;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p10;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p11;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p12;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p13;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p15;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p16;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p2;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p3;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p4;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p5;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p6;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p7;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p8;
import static com.jacoblucas.adventofcode2022.day13.PacketTest.p9;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day13Test {
    @Test
    public void testExampleInput() {
        final List<Pair<Packet, Packet>> packetPairs = ImmutableList.of(
                new Pair<>(p1, p2),
                new Pair<>(p3, p4),
                new Pair<>(p5, p6),
                new Pair<>(p7, p8),
                new Pair<>(p9, p10),
                new Pair<>(p11, p12),
                new Pair<>(p13, p13),
                new Pair<>(p15, p16));

        assertThat(Day13.getOrderedPairIndexSum(packetPairs), is(13));
    }
}
