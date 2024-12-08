package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;
import com.aoc2024.core.service.RegexService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test extends DayTestBase {

    private static final Day7 underTest = new Day7(new RegexService());

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20""";
    }

    @Override
    String getInputPt2() {
        return """
           156: 15 6
           7290: 6 8 6 15
           192: 17 8 14""";
    }

    @Override
    String expectedPt1() {
        return "3749";
    }

    @Override
    String expectedPt2() {
        return "7638";
    }

    @Test
    void allMult() {
        String expected = "1";
        Input data = TestHelper.convertToInput("1: 1 1 1 1 1 1 1 1 1 1");

        assertEquals(expected, underTest.getAnswer1(data));
    }

}
