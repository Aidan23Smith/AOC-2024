package com.aoc2024.core.day;

import com.aoc2024.core.config.RowColumnConfig;
import com.aoc2024.core.service.RegexService;

public class Day14Test extends DayTestBase {

    private static final Day14 underTest = new Day14(new RegexService(), new RowColumnConfig.RowColumn(7, 11));

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3""";
    }

    @Override
    String getInputPt2() {
        return """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3""";
    }

    @Override
    String expectedPt1() {
        return "12";
    }

    @Override
    String expectedPt2() {
        return "?";
    }

}
