package com.jacoblucas.adventofcode2022.day25;

/**
 * "SNAFU works the same way, except it uses powers of five instead of ten.
 * Starting from the right, you have a ones place, a fives place, a twenty-fives place,
 * a one-hundred-and-twenty-fives place, and so on. It's that easy!"
 */
public final class SnafuConverter {
    public static String decToSnafu(final long decimal) {
        long dec = decimal;
        int len = (int) Math.ceil(Math.log(2 * dec) / Math.log(5));
        StringBuilder snafu = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            final double place = Math.pow(5, i);
            long value = Math.round(dec / place);
            dec -= value * place;
            snafu.append(value == -2L ? "=" : value == -1L ? "-" : value);
        }
        return snafu.toString();
    }

    public static long snafuToDec(final String snafu) {
        int places = snafu.length() - 1;
        long dec = 0;
        for (int i = 0; i < snafu.length(); i++) {
            final char ch = snafu.charAt(i);
            int val = switch (ch) {
                case '=' -> -2;
                case '-' -> -1;
                default  -> Integer.parseInt(""+ch);
            };
            dec += val * Math.pow(5, places--);
        }
        return dec;
    }
}
