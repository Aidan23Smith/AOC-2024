package com.aoc2024.in.service;

import com.aoc2024.api.model.Coordinate;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReadInputServiceTest {

    private final ReadInputService underTest = new ReadInputService();

    @Test
    void getInput_validInput() throws IOException {
        Set<Coordinate> expected = Set.of(
            new Coordinate(0, 0, 'S'),
            new Coordinate(1, 0, 'a'),
            new Coordinate(2, 0, 'm'),
            new Coordinate(3, 0, 'p'),
            new Coordinate(4, 0, 'l'),
            new Coordinate(5, 0, 'e'),
            new Coordinate(0, 1, 'I'),
            new Coordinate(1, 1, 'n'),
            new Coordinate(2, 1, ' '),
            new Coordinate(3, 1, 'p'),
            new Coordinate(4, 1, 'u'),
            new Coordinate(5, 1, 't')
        );

        assertEquals(expected, underTest.getInput(1));
    }

    @Test
    void getInput_noFile() {
         assertThrows(NullPointerException.class, () -> underTest.getInput(-1));
    }

}
