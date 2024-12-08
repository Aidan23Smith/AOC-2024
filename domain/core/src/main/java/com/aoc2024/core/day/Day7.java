package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;
import com.aoc2024.core.service.RegexService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Day7 implements Day {

    private final RegexService regexService;

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(input.getRowStrings().stream()
                                  .map(row -> regexService.findPattern(row, "([0-9]+)").stream()
                                      .map(Long::valueOf)
                                      .toList())
                                  .filter(this::checkRowCanBeMadePt1)
                                  .map(List::getFirst)
                                  .reduce(0L, Long::sum));
    }

    @Override
    public String getAnswer2(Input input) {
        return String.valueOf(input.getRowStrings().stream()
                                  .map(row -> regexService.findPattern(row, "([0-9]+)").stream()
                                      .map(Long::valueOf)
                                      .toList())
                                  .filter(this::checkRowCanBeMadePt2)
                                  .map(List::getFirst)
                                  .reduce(0L, Long::sum));
    }

    private boolean checkRowCanBeMadePt1(List<Long> numbers) {
        long toMake = numbers.getFirst();
        List<Long> toUse = numbers.subList(1, numbers.size());

        return IntStream.range(0, (int) Math.pow(2, toUse.size() - 1))
            .mapToObj(combination -> StringUtils.leftPad(Integer.toBinaryString(combination), toUse.size() - 1, '0'))
            .anyMatch(combination -> {
                long current = toUse.getFirst();
                for (int i = 0; i < (toUse.size() - 1); i++) {
                    if (combination.charAt(i) == '0') {
                        current += toUse.get(i + 1);
                    } else {
                        current *= toUse.get(i + 1);
                    }
                }
                return current == toMake;
            });
    }

    private boolean checkRowCanBeMadePt2(List<Long> numbers) {
        long toMake = numbers.getFirst();
        List<Long> toUse = numbers.subList(1, numbers.size());

        return IntStream.range(0, (int) Math.pow(3, toUse.size() - 1))
            .mapToObj(combination -> StringUtils.leftPad(Integer.toString(combination, 3), toUse.size() - 1, '0'))
            .anyMatch(combination -> {
                long current = toUse.getFirst();
                for (int i = 0; i < (toUse.size() - 1); i++) {
                    if (combination.charAt(i) == '0') {
                        current += toUse.get(i + 1);
                    } else if (combination.charAt(i) == '1') {
                        current *= toUse.get(i + 1);
                    } else {
                        current = Long.parseLong(String.valueOf(current) + toUse.get(i + 1));
                    }
                }
                return current == toMake;
            });
    }

}
