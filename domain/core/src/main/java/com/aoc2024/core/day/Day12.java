package com.aoc2024.core.day;

import com.aoc2024.api.model.Coordinate;
import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Day12 implements Day {

    @Override
    public String getAnswer1(Input input) {
        Set<Set<Coordinate>> arranged = arrange(input);

        return String.valueOf(arranged.stream()
                                  .map(arrangedPot -> arrangedPot.size() * findPerimeter(arrangedPot))
                                  .reduce(Long::sum)
                                  .orElse(0L));
    }

    @Override
    public String getAnswer2(Input input) {
        Set<Set<Coordinate>> arranged = arrange(input);
        return String.valueOf(arranged.stream()
                                  .map(arrangedPot -> arrangedPot.size() * countSides(arrangedPot))
                                  .reduce(Integer::sum)
                                  .orElse(0));
    }

    private Set<Set<Coordinate>> arrange(Input input) {
        return input.characterToCoordinateMap().values().stream()
            .map(this::arrangeColour)
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
    }

    private Set<Set<Coordinate>> arrangeColour(Set<Coordinate> colourPots) {
        Set<Set<Coordinate>> arranged = new HashSet<>();
        while (!colourPots.isEmpty()) {
            Queue<Coordinate> toAdd = new PriorityQueue<>(Set.of(colourPots.iterator().next()));
            Set<Coordinate> currentPot = new HashSet<>();

            while (!toAdd.isEmpty()) {
                Coordinate current = toAdd.remove();
                colourPots.remove(current);
                toAdd.addAll(colourPots.stream()
                                 .filter(notPlaced -> notPlaced.distanceFrom(current) == 1)
                                 .collect(Collectors.toSet()));
                currentPot.add(current);
                colourPots.removeAll(toAdd);
            }
            arranged.add(currentPot);
        }
        return arranged;
    }

    private long findPerimeter(Set<Coordinate> arrangedPot) {
        return arrangedPot.stream()
            .map(onePlant -> arrangedPot.stream()
                .filter(pot -> pot.distanceFrom(onePlant) == 1)
                .count())
            .map(adjacentCount -> 4 - adjacentCount)
            .reduce(Long::sum)
            .orElse(0L);
    }

    private int countSides(Set<Coordinate> arrangedPot) {
        int leftSides = topMost(leftMost(arrangedPot)).size();
        int rightSides = topMost(rightMost(arrangedPot)).size();
        int topSides = leftMost(topMost(arrangedPot)).size();
        int bottomSides = leftMost(bottomMost(arrangedPot)).size();

        return leftSides + rightSides + topSides + bottomSides;
    }

    private Set<Coordinate> leftMost(Set<Coordinate> pots) {
        return bottomMost(pots, -1, 0);
    }

    private Set<Coordinate> topMost(Set<Coordinate> pots) {
        return bottomMost(pots, 0, -1);
    }

    private Set<Coordinate> rightMost(Set<Coordinate> pots) {
        return bottomMost(pots, 1, 0);
    }

    private Set<Coordinate> bottomMost(Set<Coordinate> pots) {
        return bottomMost(pots, 0, 1);
    }

    private Set<Coordinate> bottomMost(Set<Coordinate> pots, int checkX, int checkY) {
        return pots.stream()
            .filter(pot -> !pots.contains(new Coordinate(pot.x() + checkX, pot.y() + checkY, pot.character())))
            .collect(Collectors.toSet());
    }

}
