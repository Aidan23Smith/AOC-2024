package com.aoc2024.core.day;

public class Day11Test extends DayTestBase {

    private static final Day11 underTest = new Day11();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return "125 17";
    }

    @Override
    String getInputPt2() {
        return "0";
    }

    @Override
    String expectedPt1() {
        return "55312";
    }

    @Override
    String expectedPt2() {
        return "0";
    }

}
