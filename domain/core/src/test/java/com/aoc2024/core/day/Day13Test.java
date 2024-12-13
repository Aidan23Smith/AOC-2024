package com.aoc2024.core.day;

import com.aoc2024.core.service.RegexService;

public class Day13Test extends DayTestBase {

    private static final Day13 underTest = new Day13(new RegexService());

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279""";
    }

    @Override
    String getInputPt2() {
        return """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279""";
    }

    @Override
    String expectedPt1() {
        return "480";
    }

    @Override
    String expectedPt2() {
        return "?";
    }

}
