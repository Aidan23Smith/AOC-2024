package com.aoc2024.core.day;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test extends DayTestBase {

    private static final Day5 underTest = new Day5();

    @Override
    Day getUnderTest() {
        return underTest;
    }

    @Override
    String getInputPt1() {
        return """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47""";
    }

    @Override
    String getInputPt2() {
        return """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47""";
    }

    @Override
    String expectedPt1() {
        return "143";
    }

    @Override
    String expectedPt2() {
        return "123";
    }

    @Test
    void order() {
        List<Long> pages = List.of(1L, 2L, 3L);
        List<Pair<Long, Long>> ordering = List.of(Pair.of(1L, 2L),
                                                  Pair.of(3L, 2L),
                                                  Pair.of(3L, 1L));

        List<Long> expected = List.of(3L, 1L, 2L);

        List<Long> ordered = underTest.orderPage(pages, ordering);

        assertEquals(expected, ordered);
    }

}
