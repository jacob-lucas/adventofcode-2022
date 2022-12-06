package com.jacoblucas.adventofcode2022.day06;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day06Test {
    private static final String BUFFER0 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
    private static final String BUFFER1 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
    private static final String BUFFER2 = "nppdvjthqldpwncqszvftbrmjlhg";
    private static final String BUFFER3 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
    private static final String BUFFER4 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

    @Test
    public void testDetectStartOfPacketMarkerShortString() {
        assertThat(Day06.detectStartOfPacketMarker("abd", 4), is(-1));
    }

    @Test
    public void testDetectStartOfPacketMarker() {
        assertThat(Day06.detectStartOfPacketMarker(BUFFER0, 4), is(7));
        assertThat(Day06.detectStartOfPacketMarker(BUFFER1, 4), is(5));
        assertThat(Day06.detectStartOfPacketMarker(BUFFER2, 4), is(6));
        assertThat(Day06.detectStartOfPacketMarker(BUFFER3, 4), is(10));
        assertThat(Day06.detectStartOfPacketMarker(BUFFER4, 4), is(11));
    }
}
