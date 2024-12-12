package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test extends DayTestBase {

    private static final Day12 underTest = new Day12();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            AAAA
            BBCD
            BBCC
            EEEC""";
    }

    @Override
    String getInputPt2() {
        return """
            AAAA
            BBCD
            BBCC
            EEEC""";
    }

    @Override
    String expectedPt1() {
        return "140";
    }

    @Override
    String expectedPt2() {
        return "80";
    }

    @Test
    void part1_extra1() {
        Input input = TestHelper.convertToInput("""
                                                    OOOOO
                                                    OXOXO
                                                    OOOOO
                                                    OXOXO
                                                    OOOOO""");
        assertEquals("772", underTest.getAnswer1(input));
    }

    @Test
    void part1_extra2() {
        Input input = TestHelper.convertToInput("""
                                                    RRRRIICCFF
                                                    RRRRIICCCF
                                                    VVRRRCCFFF
                                                    VVRCCCJFFF
                                                    VVVVCJJCFE
                                                    VVIVCCJJEE
                                                    VVIIICJJEE
                                                    MIIIIIJJEE
                                                    MIIISIJEEE
                                                    MMMISSJEEE""");
        assertEquals("1930", underTest.getAnswer1(input));
    }

    @Test
    void part2_extra1() {
        Input input = TestHelper.convertToInput("""
                                                    EEEEE
                                                    EXXXX
                                                    EEEEE
                                                    EXXXX
                                                    EEEEE""");
        assertEquals("236", underTest.getAnswer2(input));
    }

    @Test
    void part2_extra2() {
        Input input = TestHelper.convertToInput("""
                                                    AAAAAA
                                                    AAABBA
                                                    AAABBA
                                                    ABBAAA
                                                    ABBAAA
                                                    AAAAAA""");
        assertEquals("368", underTest.getAnswer2(input));
    }

    @Test
    void part1_extra3() {
        Input input = TestHelper.convertToInput("""
                                                    RRRRIICCFF
                                                    RRRRIICCCF
                                                    VVRRRCCFFF
                                                    VVRCCCJFFF
                                                    VVVVCJJCFE
                                                    VVIVCCJJEE
                                                    VVIIICJJEE
                                                    MIIIIIJJEE
                                                    MIIISIJEEE
                                                    MMMISSJEEE""");
        assertEquals("1206", underTest.getAnswer2(input));
    }

}
