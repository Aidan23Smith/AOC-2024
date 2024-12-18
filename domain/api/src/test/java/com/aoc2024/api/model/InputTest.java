package com.aoc2024.api.model;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputTest {

    private final Input underTest = new Input(Set.of(
        new Coordinate(0, 0, 'a'),
        new Coordinate(1, 0, 'b'),
        new Coordinate(2, 0, 'c'),
        new Coordinate(0, 1, 'b'),
        new Coordinate(1, 1, 'c'),
        new Coordinate(2, 1, 'a'),
        new Coordinate(0, 2, 'c'),
        new Coordinate(1, 2, 'a'),
        new Coordinate(2, 2, 'b')
    ));

    @Test
    void getRow() {
        Set<Coordinate> expected = Set.of(
            new Coordinate(0, 1, 'b'),
            new Coordinate(1, 1, 'c'),
            new Coordinate(2, 1, 'a')
        );

        assertEquals(expected, underTest.getRow(1));
    }

    @Test
    void getRowString() {
        String expected = "bca";
        assertEquals(expected, underTest.getRowString(1));
    }

    @Test
    void getUnorderedRowStrings() {
        Set<String> expected = Set.of("abc", "bca", "cab");
        assertEquals(expected, underTest.getUnorderedRowStrings());
    }

    @Test
    void getRowStrings() {
        List<String> expected = List.of("abc", "bca", "cab");
        assertEquals(expected, underTest.getRowStrings());
    }

    @Test
    void getRowStringDelim() {
        List<String> expected = List.of("a", "c");

        assertEquals(expected, underTest.getRowString(0, "b"));
    }

    @Test
    void getUnorderedRows() {
        Set<String> expected = Set.of("abc", "bca", "cab");
        assertEquals(expected, underTest.getUnorderedRows());
    }

    @Test
    void getRowStringsDelim() {
        List<List<String>> expected = List.of(List.of("a", "c"), List.of("ca"), List.of("ca"));

        assertEquals(expected, underTest.getRowStrings("b"));
    }

    @Test
    void getRowNumbers() {
        Input underTest = new Input(Set.of(
            new Coordinate(0, 0, '1'),
            new Coordinate(1, 0, ' '),
            new Coordinate(2, 0, '3')));

        List<List<Long>> expected = List.of(List.of(1L, 3L));

        assertEquals(expected, underTest.getRowNumbers(" "));
    }

    @Test
    void getColumn() {
        Set<Coordinate> expected = Set.of(
            new Coordinate(1, 0, 'b'),
            new Coordinate(1, 1, 'c'),
            new Coordinate(1, 2, 'a')
        );

        assertEquals(expected, underTest.getColumn(1));
    }

    @Test
    void getColumnStrings() {
        Input underTest = new Input(Set.of(
            new Coordinate(0, 0, '1'),
            new Coordinate(1, 0, ' '),
            new Coordinate(2, 0, '2'),
            new Coordinate(0, 1, '2'),
            new Coordinate(1, 1, ' '),
            new Coordinate(2, 1, '3'),
            new Coordinate(0, 2, '3'),
            new Coordinate(1, 2, ' '),
            new Coordinate(2, 2, '4')
        ));

        List<String> expected = List.of("123", "234");

        assertEquals(expected, underTest.getColumnStrings(" "));
    }

    @Test
    void getColumnValues() {
        Input underTest = new Input(Set.of(
            new Coordinate(0, 0, '1'),
            new Coordinate(1, 0, ' '),
            new Coordinate(2, 0, '2'),
            new Coordinate(0, 1, '2'),
            new Coordinate(1, 1, ' '),
            new Coordinate(2, 1, '3'),
            new Coordinate(0, 2, '3'),
            new Coordinate(1, 2, ' '),
            new Coordinate(2, 2, '4')
        ));

        List<List<String>> expected = List.of(List.of("1", "2", "3"), List.of("2", "3", "4"));

        assertEquals(expected, underTest.getColumnValues(" "));
    }

    @Test
    void getColumnNumbers() {
        Input underTest = new Input(Set.of(
            new Coordinate(0, 0, '1'),
            new Coordinate(1, 0, ' '),
            new Coordinate(2, 0, '2'),
            new Coordinate(0, 1, '2'),
            new Coordinate(1, 1, ' '),
            new Coordinate(2, 1, '3'),
            new Coordinate(0, 2, '3'),
            new Coordinate(1, 2, ' '),
            new Coordinate(2, 2, '4')
        ));

        List<List<Long>> expected = List.of(List.of(1L, 2L, 3L), List.of(2L, 3L, 4L));

        assertEquals(expected, underTest.getColumnNumbers(" "));
    }

    @Test
    void coordinatesWithCharacter() {
        Set<Coordinate> expected = Set.of(
            new Coordinate(0, 0, 'a'),
            new Coordinate(1, 2, 'a'),
            new Coordinate(2, 1, 'a')
        );

        assertEquals(expected, underTest.coordinatesWithCharacter('a'));
    }

    @Test
    void pairsWithCharacter() {
        Set<Pair<Integer, Integer>> expected = Set.of(Pair.of(0, 0), Pair.of(2, 1), Pair.of(1, 2));

        assertEquals(expected, underTest.pairsWithCharacter('a'));
    }

    @Test
    void sorted() {
        List<Coordinate> expected = List.of(
            new Coordinate(0, 0, 'a'),
            new Coordinate(1, 0, 'b'),
            new Coordinate(2, 0, 'c'),
            new Coordinate(0, 1, 'b'),
            new Coordinate(1, 1, 'c'),
            new Coordinate(2, 1, 'a'),
            new Coordinate(0, 2, 'c'),
            new Coordinate(1, 2, 'a'),
            new Coordinate(2, 2, 'b')
        );

        assertEquals(expected, underTest.sorted());
    }

    @Test
    void maxColumnInRow() {
        assertEquals(2, underTest.maxColumnInRow(1));
    }

    @Test
    void numberOfRows() {
        assertEquals(3, underTest.numberOfRows());
    }

    @Test
    void testToString() {
        String expected = """
            abc
            bca
            cab""";
        assertEquals(expected, underTest.toString());
    }

    @Test
    void testGet() {
        Coordinate expected = new Coordinate(0, 1, 'b');
        assertEquals(expected, underTest.get(0, 1));
    }

    @Test
    void testFind() {
        Optional<Coordinate> expected = Optional.empty();
        assertEquals(expected, underTest.find(0, -1));
    }

    @Test
    void testBetween() {
        Set<Coordinate> expected = Set.of(
            new Coordinate(0, 1, 'b'),
            new Coordinate(0, 2, 'c'),
            new Coordinate(1, 1, 'c'),
            new Coordinate(1, 2, 'a')
        );
        assertEquals(expected, underTest.between(0, 1, 1, 2));
    }

    @Test
    void splitByBlankRow() {
        Input underTest = new Input(Set.of(
            new Coordinate(0, 0, 'a'),
            new Coordinate(1, 0, 'b'),
            new Coordinate(2, 0, 'c'),
            new Coordinate(0, 2, 'c'),
            new Coordinate(1, 2, 'a'),
            new Coordinate(2, 2, 'b')
        ));
        List<Input> expected = List.of(
            new Input(Set.of(new Coordinate(0, 0, 'a'),
                             new Coordinate(1, 0, 'b'),
                             new Coordinate(2, 0, 'c'))),
            new Input(Set.of(new Coordinate(0, 0, 'c'),
                             new Coordinate(1, 0, 'a'),
                             new Coordinate(2, 0, 'b')))

        );
        assertEquals(expected, underTest.splitByBlankRow());
    }

    @Test
    void characterToPairsMap() {
        Map<Character, Set<Pair<Integer, Integer>>> expected = Map.of(
            'a', Set.of(
                Pair.of(0, 0),
                Pair.of(2, 1),
                Pair.of(1, 2)
            ),
            'b', Set.of(
                Pair.of(1, 0),
                Pair.of(0, 1),
                Pair.of(2, 2)
            ),
            'c', Set.of(
                Pair.of(2, 0),
                Pair.of(1, 1),
                Pair.of(0, 2)
            )
        );
        assertEquals(expected, underTest.characterToPairsMap());
    }

    @Test
    void characterToCoordinateMap() {
        Map<Character, Set<Coordinate>> expected = Map.of(
            'a', Set.of(
                new Coordinate(0, 0, 'a'),
                new Coordinate(2, 1, 'a'),
                new Coordinate(1, 2, 'a')
            ),
            'b', Set.of(
                new Coordinate(1, 0, 'b'),
                new Coordinate(0, 1, 'b'),
                new Coordinate(2, 2, 'b')
            ),
            'c', Set.of(
                new Coordinate(2, 0, 'c'),
                new Coordinate(1, 1, 'c'),
                new Coordinate(0, 2, 'c')
            )
        );
        assertEquals(expected, underTest.characterToCoordinateMap());
    }

    @Test
    void characterToPairsMap_ignoring() {
        Map<Character, Set<Pair<Integer, Integer>>> expected = Map.of(
            'a', Set.of(
                Pair.of(0, 0),
                Pair.of(2, 1),
                Pair.of(1, 2)
            )
        );
        assertEquals(expected, underTest.characterToPairsMap('b', 'c'));
    }

    @Test
    void adjacentCoordinates() {
        Set<Coordinate> expected = Set.of(
            new Coordinate(1, 0, 'b'),
            new Coordinate(0, 1, 'b'),
            new Coordinate(2, 1, 'a'),
            new Coordinate(1, 2, 'a')
        );
        assertEquals(expected, underTest.adjacentCoordinates(underTest.get(1, 1)));
    }

}