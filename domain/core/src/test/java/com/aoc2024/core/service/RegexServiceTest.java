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

}
