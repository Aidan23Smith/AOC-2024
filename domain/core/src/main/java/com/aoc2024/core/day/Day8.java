package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Day8 implements Day {

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(input.characterToPairsMap('.')
                                  .values()
                                  .stream()
                                  .map(antennas -> findPossible(antennas, input))
                                  .flatMap(Set::stream)
                                  .collect(Collectors.toSet())
                                  .size());
    }

    @Override
    public String getAnswer2(Input input) {
        return String.valueOf(input.characterToPairsMap('.')
                                  .values()
                                  .stream()
                                  .map(antennas -> findPossiblePt2(antennas, input))
                                  .flatMap(Set::stream)
                                  .filter(antenna -> inRange(antenna, input))
                                  .collect(Collectors.toSet())
                                  .size());
    }

    private Set<Pair<Integer, Integer>> findPossible(Set<Pair<Integer, Integer>> antennas, Input input) {
        return antennas.stream()
            .flatMap(antenna -> antennas.stream()
                .filter(second -> second != antenna)
                .map(second -> Pair.of(antenna.getLeft() + (antenna.getLeft() - second.getLeft()),
                                       antenna.getRight() + (antenna.getRight() - second.getRight()))))
            .filter(antenna -> !antennas.contains(antenna))
            .filter(antenna -> inRange(antenna, input))
            .collect(Collectors.toSet());
    }

    private Set<Pair<Integer, Integer>> findPossiblePt2(Set<Pair<Integer, Integer>> antennas, Input input) {
        Set<Pair<Integer, Integer>> all = new HashSet<>(antennas);

        all.addAll(antennas.stream()
            .flatMap(antenna -> antennas.stream()
                .filter(second -> second != antenna)
                .flatMap(second -> {
                    List<Pair<Integer, Integer>> found = new ArrayList<>();
                    int x = antenna.getLeft() - second.getLeft();
                    int y = antenna.getRight() - second.getRight();
                    found.add(Pair.of(second.getLeft() + x, second.getRight() + y));

                    while (inRange(found.getLast(), input)) {
                        found.add(Pair.of(found.getLast().getLeft() + x, found.getLast().getRight() + y));
                    }
                    return found.stream();
                }))
            .filter(antenna -> !antennas.contains(antenna))
            .filter(antenna -> inRange(antenna, input))
            .collect(Collectors.toSet()));

        if (all.size() > 1) {
            return all;
        }
        return Collections.emptySet();
    }

    private boolean inRange(Pair<Integer, Integer> antenna, Input input) {
        return (0 <= antenna.getLeft())
               && (0 <= antenna.getRight())
               && (antenna.getRight() < input.numberOfRows())
               && (antenna.getLeft() <= input.maxColumnInRow(0));
    }

}
