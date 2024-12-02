package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class Day2 implements Day {

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(input.getRowNumbers(" ").stream()
                                  .filter(this::pt1Filter)
                                  .count());
    }

    @Override
    public String getAnswer2(Input input) {
        return String.valueOf(input.getRowNumbers(" ").stream()
                                  .filter(this::pt2Filter)
                                  .count());
    }

    boolean pt1Filter(List<Long> row) {
        if (row.get(0).equals(row.get(1))) {
            return false;
        }
        boolean isIncreasing = row.get(0) < row.get(1);

        return IntStream.range(0, row.size() - 1)
            .mapToObj(rowIndex -> compareToNext(row, rowIndex, isIncreasing))
            .reduce(true, (a, b) -> a && b);
    }

    boolean pt2Filter(List<Long> row) {
        return IntStream.range(0, row.size())
            .mapToObj(rowIndex -> {
                List<Long> subList = new ArrayList<>(row.subList(0, rowIndex));
                subList.addAll(row.subList(rowIndex + 1, row.size()));
                return subList;
            })
            .anyMatch(this::pt1Filter);
    }

    private boolean compareToNext(List<Long> row, int rowIndex, boolean isIncreasing) {
        return isIncreasing ?
               (row.get(rowIndex) < row.get(rowIndex + 1)) :
               ((row.get(rowIndex) > row.get(rowIndex + 1))
                && (Math.abs(row.get(rowIndex) - row.get(rowIndex + 1)) <= 3));
    }

}
