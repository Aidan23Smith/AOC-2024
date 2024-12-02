package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Day1 implements Day {

    @Override
    public String getAnswer1(Input input) {
        Pair<List<Long>, List<Long>> locationIds = getLeftRightLocationIds(input);
        return String.valueOf(IntStream.range(0, locationIds.getKey().size())
                                  .mapToLong(locationIdNumber -> Math.abs(locationIds.getKey().get(locationIdNumber) - locationIds.getValue().get(locationIdNumber)))
                                  .reduce(0, Long::sum));
    }

    @Override
    public String getAnswer2(Input input) {
        Pair<List<Long>, List<Long>> locationIds = getLeftRightLocationIds(input);
        return String.valueOf(locationIds.getKey().stream()
                                  .map(locationId1 -> locationIds.getValue().stream()
                                                          .filter(locationId2 -> locationId2.equals(locationId1))
                                                          .count() * locationId1
                                  )
                                  .reduce(0L, Long::sum));
    }

    private Pair<List<Long>, List<Long>> getLeftRightLocationIds(Input input) {
        List<List<Long>> rowNumbers = input.getRowNumbers(" ");

        List<Long> firstLocation = rowNumbers.stream().map(List::getFirst).sorted().toList();
        List<Long> secondLocation = rowNumbers.stream().map(List::getLast).sorted().toList();

        return Pair.of(firstLocation, secondLocation);
    }
}
