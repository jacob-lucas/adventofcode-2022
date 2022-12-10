package com.jacoblucas.adventofcode2022.day07;

import com.jacoblucas.adventofcode2022.utils.InputReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DirectoryTest {
    @Test
    public void testToString() {
        Directory root = new Directory("/", null);
        Directory a =  new Directory("a", root);
        Directory b =  new Directory("b", a);
        Directory c =  new Directory("c", b);

        assertThat(c.toString(), is("/a/b/c"));
    }

    @Test
    public void testIsRoot() {
        Directory root = new Directory("/", null);
        Directory a =  new Directory("a", root);

        assertThat(root.isRoot(), is(true));
        assertThat(a.isRoot(), is(false));
    }

    @Test
    public void testEmptyDirectorySize() {
        Directory root = new Directory("/", null);
        assertThat(root.getSize(), is(0L));
    }

    @Test
    public void testSizeForHierarchy() {
        Directory root = new Directory("/", null);
        Directory a =  new Directory("a", root);
        Directory b =  new Directory("b", a);
        Directory c =  new Directory("c", root);

        root.addFile(a, c);
        a.addFile(b);

        a.addFile(new DataFile("1", 1));
        a.addFile(new DataFile("2", 2));
        a.addFile(new DataFile("3", 3));

        b.addFile(new DataFile("4", 4));
        b.addFile(new DataFile("5", 5));

        c.addFile(new DataFile("6", 6));

        assertThat(a.getSize(), is(15L));
        assertThat(b.getSize(), is(9L));
        assertThat(c.getSize(), is(6L));
        assertThat(root.getSize(), is(21L));
    }

    @Test
    public void testSizeForTestInput() throws IOException {
        final List<String> testInput = InputReader.readFile("src/test/resources/","day07-test-input.txt");
        final Directory root = Day07.parseFileSystem(testInput);

        assertThat(root.getSize(), is(48381165L));

        final Directory d = root.getSubDirectories().stream()
                .filter(dir -> dir.getName().equals("d"))
                .findFirst()
                .get();

        assertThat(d.getSize(), is(24933642L));
    }
}
