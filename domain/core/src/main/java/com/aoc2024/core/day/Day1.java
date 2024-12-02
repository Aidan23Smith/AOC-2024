package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Day1 implements Day {

    @Override
    public String getAnswer1(Input input) {
        List<List<Long>> columns = input.getColumnNumbers(" ");
        List<Long> firstLocation = columns.getFirst().stream().sorted().toList();
        List<Long> secondLocation = columns.getLast().stream().sorted().toList();

        return String.valueOf(IntStream.range(0, firstLocation.size())
                                  .mapToLong(locationIdNumber -> Math.abs(firstLocation.get(locationIdNumber) - secondLocation.get(locationIdNumber)))
                                  .reduce(0, Long::sum));
    }

    @Override
    public String getAnswer2(Input input) {
        List<List<Long>> columns = input.getColumnNumbers(" ");
        List<Long> firstLocation = columns.getFirst();
        List<Long> secondLocation = columns.getLast();

        return String.valueOf(firstLocation.stream()
                                  .map(locationId1 -> secondLocation.stream()
                                                          .filter(locationId2 -> locationId2.equals(locationId1))
                                                          .count() * locationId1
                                  )
                                  .reduce(0L, Long::sum));
    }

}
