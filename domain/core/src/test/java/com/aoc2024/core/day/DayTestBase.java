package com.aoc2024.core.day;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class DayTestBase {

    private final Day underTest = getUnderTest();

    @Test
    void pt1() {
        assertEquals(expectedPt1(), underTest.getAnswer1(TestHelper.convertToInput(getInputPt1())));
    }

    @Test
    void pt2() {
        assertEquals(expectedPt2(), underTest.getAnswer2(TestHelper.convertToInput(getInputPt2())));
    }

    abstract Day getUnderTest();

    abstract String getInputPt1();

    abstract String getInputPt2();

    abstract String expectedPt1();

    abstract String expectedPt2();

}
