package com.aoc2024.core.day;

public class Day6Test extends DayTestBase {

    private static final Day6 underTest = new Day6();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...""";
    }

    @Override
    String getInputPt2() {
        return """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...""";
    }

    @Override
    String expectedPt1() {
        return "41";
    }

    @Override
    String expectedPt2() {
        return "6";
    }

}
