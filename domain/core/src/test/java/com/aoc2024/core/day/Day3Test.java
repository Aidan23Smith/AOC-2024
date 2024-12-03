package com.aoc2024.core.day;

import com.aoc2024.core.service.RegexService;

public class Day3Test extends DayTestBase {

    private static final Day3 underTest = new Day3(new RegexService());

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""";
    }

    @Override
    String getInputPt2() {
        return """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))""";
    }

    @Override
    String expectedPt1() {
        return "161";
    }

    @Override
    String expectedPt2() {
        return "48";
    }

}
