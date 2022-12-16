package com.jacoblucas.adventofcode2022.day13;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

// Packet data consists of lists and integers. Each list starts with [, ends with ], and
// contains zero or more comma-separated values (either integers or other lists).
// Each packet is always a list and appears on its own line.
public class Packet extends ArrayList<Object> implements Comparable<Packet> {
    private static final char OPEN = '[';

    private static final char CLOSE = ']';

    private static final char SEPARATOR = ',';

    static int parseIndex = 1;

    public Packet(final List<Object> data) {
        super(data);
    }

    public static Packet parse(final String str) {
        parseIndex = 1;
        return parseHelper(str);
    }

    private static Packet parseHelper(final String str) {
        List<Object> list = new ArrayList<>();

        while (parseIndex < str.length() - 1) {
            char c = str.charAt(parseIndex++);
            if (c == OPEN) {
                list.add(parseHelper(str));
            } else if (c == CLOSE) {
                break;
            } else if (c == SEPARATOR) {
                continue; // do nothing
            } else {
                final int src = parseIndex - 1;
                int dest1 = str.indexOf(",", parseIndex);
                int dest2 = str.indexOf("]", parseIndex);
                String substring;
                if (dest1 > 0 && dest2 > 0) {
                    substring = str.substring(src, Math.min(dest1, dest2));
                } else if (dest1 > 0) {
                    substring = str.substring(src, dest1);
                } else {
                    substring = str.substring(src, dest2);
                }
                list.add(Integer.parseInt(substring));
                if (substring.length() > 1) {
                    parseIndex += substring.length() - 1;
                }
            }
        }

        return new Packet(list);
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param other the object to be compared.
     * @return Returns a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(final Packet other) {
        return compare(this, other);
    }

    public int compare(final Object left, final Object right) {
        if (left instanceof Integer leftInt && right instanceof Integer rightInt) {
            // both are ints, lower should come first
            return Integer.compare(leftInt, rightInt);
        } else if (left instanceof List<?> leftList && right instanceof List<?> rightList) {
            // both are lists

            // check for empty lists
            if (leftList.isEmpty()) {
                return rightList.isEmpty() ? 0 : -1;
            }

            // compare value by value
            for (int i = 0; i < Math.min(leftList.size(), rightList.size()); i++) {
                Object leftObj = leftList.get(i);
                Object rightObj = rightList.get(i);
                int result = compare(leftObj, rightObj);
                if (result != 0) {
                    return result;
                }
            }

            // check for running out of items on either side
            return Integer.compare(leftList.size(), rightList.size());
        } else {
            // mixed type, convert & compare
            return compare(
                    left instanceof Integer leftInt ? ImmutableList.of(leftInt) : left,
                    right instanceof Integer rightInt ? ImmutableList.of(rightInt) : right);
        }
    }
}
