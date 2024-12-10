package com.aoc2024.core.day;

public class Day10Test extends DayTestBase {

    private static final Day10 underTest = new Day10();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732""";
    }

    @Override
    String getInputPt2() {
        return """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732""";
    }

    @Override
    String expectedPt1() {
        return "36";
    }

    @Override
    String expectedPt2() {
        return "81";
    }

}
