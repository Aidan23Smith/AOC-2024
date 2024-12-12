package com.aoc2024.core.usecase;

import com.aoc2024.api.model.AnswerRequest;
import com.aoc2024.api.port.AnswerService;
import com.aoc2024.core.day.Day;
import com.aoc2024.core.day.Day1;
import com.aoc2024.core.day.Day10;
import com.aoc2024.core.day.Day11;
import com.aoc2024.core.day.Day12;
import com.aoc2024.core.day.Day2;
import com.aoc2024.core.day.Day3;
import com.aoc2024.core.day.Day4;
import com.aoc2024.core.day.Day5;
import com.aoc2024.core.day.Day6;
import com.aoc2024.core.day.Day7;
import com.aoc2024.core.day.Day8;
import com.aoc2024.core.day.Day9;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final Day1 day1;
    private final Day2 day2;
    private final Day3 day3;
    private final Day4 day4;
    private final Day5 day5;
    private final Day6 day6;
    private final Day7 day7;
    private final Day8 day8;
    private final Day9 day9;
    private final Day10 day10;
    private final Day11 day11;
    private final Day12 day12;

    @Override
    public String getPt1(AnswerRequest answerRequest) {
        return getDay(answerRequest.day()).getAnswer1(answerRequest.input());
    }

    @Override
    public String getPt2(AnswerRequest answerRequest) {
        return getDay(answerRequest.day()).getAnswer2(answerRequest.input());
    }

    private Day getDay(int day) {
        return switch (day) {
            case 1 -> day1;
            case 2 -> day2;
            case 3 -> day3;
            case 4 -> day4;
            case 5 -> day5;
            case 6 -> day6;
            case 7 -> day7;
            case 8 -> day8;
            case 9 -> day9;
            case 10 -> day10;
            case 11 -> day11;
            case 12 -> day12;
            default -> throw new NotImplementedException();
        };
    }
}
