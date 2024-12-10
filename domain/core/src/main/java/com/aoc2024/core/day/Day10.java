package com.aoc2024.core.day;

import com.aoc2024.api.model.Coordinate;
import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Day10 implements Day {

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(input.coordinatesWithCharacter('0').stream()
                                  .map(trailHead -> findNumberOfEndPointsFromHead(trailHead, input))
                                  .reduce(0, Integer::sum));
    }

    @Override
    public String getAnswer2(Input input) {
        return String.valueOf(findNumberOfWaysToGetToEachEndpoint(input).values().stream().reduce(0, Integer::sum));
    }

    private Integer findNumberOfEndPointsFromHead(Coordinate trailHead, Input input) {
        Set<Coordinate> possibleAtHeight = Set.of(trailHead);
        for (int height = 1; height <= 9; height++) {
            possibleAtHeight = findPossibleAtHeight(possibleAtHeight, height, input);
        }
        return possibleAtHeight.size();
    }

    private Set<Coordinate> findPossibleAtHeight(Set<Coordinate> possibleAtCurrentHeight, int height, Input input) {
        return possibleAtCurrentHeight.stream()
            .map(input::adjacentCoordinates)
            .flatMap(Set::stream)
            .filter(adjacent -> adjacent.character() == Character.forDigit(height, 10))
            .collect(Collectors.toSet());
    }

    private Map<Coordinate, Integer> findNumberOfWaysToGetToEachEndpoint(Input input) {
        Map<Coordinate, Integer> coordinateToNumberOfPaths = input.coordinatesWithCharacter('0').stream()
            .collect(Collectors.toMap(coordinate -> coordinate, coordinate -> 1));

        for (int height = 1; height <= 9; height++) {
            coordinateToNumberOfPaths = findNextCoordinateToNumberOfPaths(coordinateToNumberOfPaths, height, input);
        }

        return coordinateToNumberOfPaths;
    }

    private Map<Coordinate, Integer> findNextCoordinateToNumberOfPaths(Map<Coordinate, Integer> coordinateToNumberOfPaths, int height, Input input) {
        Set<Coordinate> possibleAtHeight = coordinateToNumberOfPaths.keySet();
        Map<Coordinate, Integer> nextMap = new HashMap<>();

        for (Coordinate currentCoordinate : possibleAtHeight) {
            updateNextMap(currentCoordinate, height, nextMap, coordinateToNumberOfPaths, input);
        }
        return nextMap;
    }

    private void updateNextMap(Coordinate currentCoordinate, int height, Map<Coordinate, Integer> nextMap, Map<Coordinate, Integer> coordinateToNumberOfPaths, Input input) {
        input.adjacentCoordinates(currentCoordinate).stream()
            .filter(adjacent -> adjacent.character() == Character.forDigit(height, 10))
            .forEach(adjacent -> updateNextMap(nextMap, adjacent, coordinateToNumberOfPaths, currentCoordinate));
    }

    private void updateNextMap(Map<Coordinate, Integer> nextMap, Coordinate adjacent, Map<Coordinate, Integer> coordinateToNumberOfPaths, Coordinate currentCoordinate) {
        if (nextMap.containsKey(adjacent)) {
            nextMap.put(adjacent, nextMap.get(adjacent) + coordinateToNumberOfPaths.get(currentCoordinate));
        } else {
            nextMap.put(adjacent, coordinateToNumberOfPaths.get(currentCoordinate));
        }
    }

}
