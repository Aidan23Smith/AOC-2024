package com.aoc2024.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RegexServiceTest {

    @InjectMocks
    RegexService regexService;

    @Test
    void findPattern() {
        List<String> expected = List.of("1", "2", "3", "4");

        List<String> actual = regexService.findPattern("123abc4", "([0-9])");

        assertEquals(expected, actual);
    }

    @Test
    void findPattern_example() {
        List<String> expected = List.of("1", "3 blue, 4 red", "1 red, 2 green, 6 blue", "2 green");

        List<String> actual = regexService.findPattern("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", "[Game ([0-9]):|;]? ([0-9a-z, ]+)");

        assertEquals(expected, actual);
    }

    @Test
    void findPattern_day3_pt1() {
        List<String> expected = List.of("2,4", "5,5", "11,8", "8,5");

        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

        List<String> actual = regexService.findPattern(input, "mul\\(([0-9]{1,3},[0-9]{1,3})\\)");

        assertEquals(expected, actual);
    }

    @Test
    void findPattern_day7() {
        List<String> expected = List.of("3267", "81", "40", "27");

        String input = "3267: 81 40 27";

        List<String> actual = regexService.findPattern(input, "([0-9]+)");

        assertEquals(expected, actual);
    }

}
