package com.aoc2024.core.day;

public class Day9Test extends DayTestBase {

    private static final Day9 underTest = new Day9();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return "2333133121414131402";
    }

    @Override
    String getInputPt2() {
        return "2333133121414131402";
    }

    @Override
    String expectedPt1() {
        return "1928";
    }

    @Override
    String expectedPt2() {
        return "2858";
    }

}
