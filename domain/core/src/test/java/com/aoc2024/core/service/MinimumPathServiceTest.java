package com.aoc2024.core.service;

import com.aoc2024.api.model.Coordinate;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MinimumPathServiceTest {

    private final MinimumPathService underTest = new MinimumPathService();

    @Test
    void getMinimumPath() {
        Coordinate coordinate1 = mock(Coordinate.class);
        Coordinate coordinate2 = mock(Coordinate.class);
        Coordinate coordinate3 = mock(Coordinate.class);
        Coordinate coordinate4 = mock(Coordinate.class);
        Coordinate coordinate5 = mock(Coordinate.class);

        underTest.addNeighbours(coordinate1, coordinate2, 2);
        underTest.addNeighbours(coordinate2, coordinate3, 2);
        underTest.addNeighbours(coordinate3, coordinate4, 2);
        underTest.addNeighbours(coordinate1, coordinate4, 5);
        underTest.addNeighbours(coordinate4, coordinate5, 0);

        assertEquals(List.of(coordinate1, coordinate4, coordinate5), underTest.getMinimumPath(coordinate1, coordinate5));
    }

    @Test
    void getMinimumLength() {
        Coordinate coordinate1 = mock(Coordinate.class);
        Coordinate coordinate2 = mock(Coordinate.class);
        Coordinate coordinate3 = mock(Coordinate.class);
        Coordinate coordinate4 = mock(Coordinate.class);
        Coordinate coordinate5 = mock(Coordinate.class);

        underTest.addNeighbours(coordinate1, coordinate2, 2);
        underTest.addNeighbours(coordinate2, coordinate3, 2);
        underTest.addNeighbours(coordinate3, coordinate4, 2);
        underTest.addNeighbours(coordinate1, coordinate4, 5);
        underTest.addNeighbours(coordinate4, coordinate5, 0);

        assertEquals(5L, underTest.getMinimumLength(coordinate1, coordinate5));
    }

}
