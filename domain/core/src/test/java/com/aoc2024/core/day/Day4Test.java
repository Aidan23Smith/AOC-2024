package com.aoc2024.core.day;

public class Day4Test extends DayTestBase {

    private static final Day4 underTest = new Day4();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX""";
    }

    @Override
    String getInputPt2() {
        return """
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........""";
    }

    @Override
    String expectedPt1() {
        return "18";
    }

    @Override
    String expectedPt2() {
        return "9";
    }

}
