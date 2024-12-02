package com.aoc2024.core.day;

public class Day1Test extends DayTestBase {

    @Override
    Day getUnderTest() {
        return new Day1();
    }

    @Override
    String getInputPt1() {
        return """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3""";
    }

    @Override
    String getInputPt2() {
        return """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3""";
    }

    @Override
    String expectedPt1() {
        return "11";
    }

    @Override
    String expectedPt2() {
        return "31";
    }
}
