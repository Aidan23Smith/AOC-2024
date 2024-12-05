package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class Day5 implements Day {

    @Override
    public String getAnswer1(Input input) {
        return sumMiddles(input, true);
    }

    @Override
    public String getAnswer2(Input input) {
        return sumMiddles(input, false);
    }

    private String sumMiddles(Input input, boolean forValidLines) {
        List<Input> split = input.splitByBlankRow();
        List<Pair<Long, Long>> ordering = split.getFirst().getRowNumbers("\\|").stream()
            .map(row -> Pair.of(row.getFirst(), row.getLast()))
            .toList();
        List<List<Long>> allUpdates = split.getLast().getRowNumbers(",");

        return String.valueOf(allUpdates.stream()
                                  .map(update -> orderPage(update, ordering))
                                  .filter(ordered -> allUpdates.contains(ordered) == forValidLines)
                                  .mapToLong(validUpdate -> validUpdate.get((validUpdate.size() - 1) / 2))
                                  .reduce(0, Long::sum));
    }

    List<Long> orderPage(List<Long> pages, List<Pair<Long, Long>> ordering) {
        return pages.stream()
            .sorted((first, second) -> {
                if (ordering.stream().anyMatch(order -> Objects.equals(order.getLeft(), first) && Objects.equals(order.getRight(), second))) {
                    return -1;
                }

                if (ordering.stream().anyMatch(order -> Objects.equals(order.getLeft(), second) && Objects.equals(order.getRight(), first))) {
                    return 1;
                }
                return 0;
            }).toList();
    }

}
