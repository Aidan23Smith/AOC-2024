package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;
import com.aoc2024.core.service.RegexService;

import org.springframework.stereotype.Service;

import java.util.Arrays;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Day3 implements Day {

    private final RegexService regexService;

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(input.getRowStrings().stream()
                                  .map(this::multString)
                                  .reduce(0, Integer::sum));
    }

    @Override
    public String getAnswer2(Input input) {
        String complete = input.getRowStrings().stream().reduce("", (a, b) -> a + b);

        int currentIndex = 0;
        int currentTotal = 0;

        int nextIndex = complete.indexOf("don't()");
        while(currentIndex != -1) {
            currentTotal += multString(complete.substring(currentIndex, nextIndex));

            currentIndex = complete.indexOf("do()", nextIndex);
            nextIndex = complete.indexOf("don't()", currentIndex);
            nextIndex = (nextIndex == -1) ? complete.length() : nextIndex;
        }

        return String.valueOf(currentTotal);
    }

    private Integer multString(String row) {
        return regexService.findPattern(row, "mul\\(([0-9]{1,3},[0-9]{1,3})\\)").stream()
            .map(pairs -> Arrays.stream(pairs.split(","))
                .map(Integer::valueOf)
                .reduce(1, (a, b) -> a * b))
            .reduce(0, Integer::sum);
    }
}
