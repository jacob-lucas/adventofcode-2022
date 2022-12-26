package day25;

import com.jacoblucas.adventofcode2022.day25.SnafuConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SnafuConverterTest {
    @Test
    public void testDecToSnafu() {
        assertThat(SnafuConverter.decToSnafu(976), is("2=-01"));
        assertThat(SnafuConverter.decToSnafu(1747), is("1=-0-2"));
        assertThat(SnafuConverter.decToSnafu(906), is("12111"));
        assertThat(SnafuConverter.decToSnafu(198), is("2=0="));
        assertThat(SnafuConverter.decToSnafu(11), is("21"));
        assertThat(SnafuConverter.decToSnafu(201), is("2=01"));
        assertThat(SnafuConverter.decToSnafu(31), is("111"));
        assertThat(SnafuConverter.decToSnafu(1257), is("20012"));
        assertThat(SnafuConverter.decToSnafu(32), is("112"));
        assertThat(SnafuConverter.decToSnafu(353), is("1=-1="));
        assertThat(SnafuConverter.decToSnafu(107), is("1-12"));
        assertThat(SnafuConverter.decToSnafu(7), is("12"));
        assertThat(SnafuConverter.decToSnafu(3), is("1="));
        assertThat(SnafuConverter.decToSnafu(37), is("122"));
        assertThat(SnafuConverter.decToSnafu(4890), is("2=-1=0"));
    }

    @Test
    public void testSnafuToDec() {
        assertThat(SnafuConverter.snafuToDec("2=-01"), is(976L));
        assertThat(SnafuConverter.snafuToDec("1=-0-2"), is(1747L));
        assertThat(SnafuConverter.snafuToDec("12111"), is(906L));
        assertThat(SnafuConverter.snafuToDec("2=0="), is(198L));
        assertThat(SnafuConverter.snafuToDec("21"), is(11L));
        assertThat(SnafuConverter.snafuToDec("2=01"), is(201L));
        assertThat(SnafuConverter.snafuToDec("111"), is(31L));
        assertThat(SnafuConverter.snafuToDec("20012"), is(1257L));
        assertThat(SnafuConverter.snafuToDec("112"), is(32L));
        assertThat(SnafuConverter.snafuToDec("1=-1="), is(353L));
        assertThat(SnafuConverter.snafuToDec("1-12"), is(107L));
        assertThat(SnafuConverter.snafuToDec("12"), is(7L));
        assertThat(SnafuConverter.snafuToDec("1="), is(3L));
        assertThat(SnafuConverter.snafuToDec("122"), is(37L));
    }
}
