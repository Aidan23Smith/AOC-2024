package com.aoc2024.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void distance() {
        Coordinate coordinate1 = new Coordinate(1, 2, ' ');
        Coordinate coordinate2 = new Coordinate(1, 1, ' ');

        assertEquals(1, coordinate1.distanceFrom(coordinate2));
    }

    @Test
    void compareTo_equal() {
        Coordinate coordinate1 = new Coordinate(1, 2, ' ');
        Coordinate coordinate2 = new Coordinate(1, 2, ' ');

        assertEquals(0, coordinate1.compareTo(coordinate2));
    }

    @Test
    void compareTo_equalY() {
        Coordinate coordinate1 = new Coordinate(1, 2, ' ');
        Coordinate coordinate2 = new Coordinate(2, 2, ' ');

        assertEquals(-1, coordinate1.compareTo(coordinate2));
    }

    @Test
    void compareTo_differentY() {
        Coordinate coordinate1 = new Coordinate(1, 2, ' ');
        Coordinate coordinate2 = new Coordinate(1, 1, ' ');

        assertEquals(1, coordinate1.compareTo(coordinate2));
    }

}
