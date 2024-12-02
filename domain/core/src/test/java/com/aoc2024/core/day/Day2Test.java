package com.aoc2024.core.day;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day2Test extends DayTestBase {

    private static final Day2 underTest = new Day2();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9""";
    }

    @Override
    String getInputPt2() {
        return """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9""";
    }

    @Override
    String expectedPt1() {
        return "2";
    }

    @Override
    String expectedPt2() {
        return "4";
    }

    @Test
    void pt2Filter() {
        List<Long> row = List.of(1L, 3L, 2L, 4L, 5L);
        assertTrue(underTest.pt2Filter(row));
    }

    @Test
    void pt2Filter_startsEqual() {
        List<Long> row = List.of(3L, 3L, 2L, 1L);
        assertTrue(underTest.pt2Filter(row));
    }

    @Test
    void pt2Filter_skipAlternative() {
        List<Long> row = List.of(1L, 5L, 9L, 5L, 1L);
        assertFalse(underTest.pt2Filter(row));
    }

    @Test
    void pt2Filter_skipAlternative_2() {
        List<Long> row = List.of(1L, 4L, 3L, 4L, 5L);
        assertTrue(underTest.pt2Filter(row));
    }

    @Test
    void pt2Filter_skipAlternative_3() {
        List<Long> row = List.of(1L, 5L, 6L, 7L, 9L);
        assertTrue(underTest.pt2Filter(row));
    }

    @Test
    void pt2Filter_skipAlternative_4() {
        List<Long> row = List.of(1L, 2L, 3L, 4L, 1L);
        assertTrue(underTest.pt2Filter(row));
    }
}
