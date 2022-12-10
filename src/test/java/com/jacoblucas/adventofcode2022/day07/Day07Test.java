package com.jacoblucas.adventofcode2022.day07;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class Day07Test {
    /**
     * - / (dir)
     *   - a (dir)
     *     - e (dir)
     *       - i (file, size=584)
     *     - f (file, size=29116)
     *     - g (file, size=2557)
     *     - h.lst (file, size=62596)
     *   - b.txt (file, size=14848514)
     *   - c.dat (file, size=8504156)
     *   - d (dir)
     *     - j (file, size=4060174)
     *     - d.log (file, size=8033020)
     *     - d.ext (file, size=5626152)
     *     - k (file, size=7214296)
     */
    @Test
    public void testParseFileSystem() throws IOException {
        final List<String> testInput = InputReader.readFile("src/test/resources/","day07-test-input.txt");

        final Directory root = Day07.parseFileSystem(testInput);
        assertThat(root.getFiles().size(), is(4));

        assertThat(root.getFiles().stream().filter(f -> f instanceof Directory).count(), is(2L));
        assertThat(root.getFiles().stream().filter(f -> f instanceof DataFile).count(), is(2L));
    }

    @Test
    public void testFindDirectories() throws IOException {
        final List<String> testInput = InputReader.readFile("src/test/resources/","day07-test-input.txt");
        final Directory root = Day07.parseFileSystem(testInput);

        final List<Directory> filtered = Day07.findDirectories(root, 100000);
        assertThat(filtered.stream().map(Directory::getName).collect(Collectors.toList()), containsInAnyOrder("a", "e"));
    }
}
