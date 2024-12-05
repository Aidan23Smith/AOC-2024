package com.aoc2024.core.day;

import com.aoc2024.api.model.Coordinate;
import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
public class Day4 implements Day {

    private static final String XMAS = "XMAS";

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(input.coordinatesWithCharacter(XMAS.charAt(0)).stream()
                                  .flatMap(coordinate ->
                                               Arrays.stream(Direction.values())
                                                   .filter(direction ->
                                                               isCharacter(input, coordinate, direction, 1, XMAS.charAt(1))
                                                               && isCharacter(input, coordinate, direction, 2, XMAS.charAt(2))
                                                               && isCharacter(input, coordinate, direction, 3, XMAS.charAt(3)))
                                  ).count());
    }

    @Override
    public String getAnswer2(Input input) {
        return String.valueOf(input.coordinatesWithCharacter(XMAS.charAt(2)).stream()
                                  .filter(coordinate ->
                                              List.of(getCharacter(input, coordinate, Direction.UP_LEFT),
                                                      getCharacter(input, coordinate, Direction.DOWN_RIGHT))
                                                  .containsAll(List.of(XMAS.charAt(1), XMAS.charAt(3)))
                                              && List.of(getCharacter(input, coordinate, Direction.UP_LEFT),
                                                         getCharacter(input, coordinate, Direction.DOWN_RIGHT))
                                                  .containsAll(List.of(XMAS.charAt(1), XMAS.charAt(3)))
                                  ).count());
    }

    private boolean isCharacter(Input input, Coordinate coordinate, Direction direction, int distance, char character) {
        return find(input, coordinate, direction, distance)
            .map(m -> m.character() == character)
            .orElse(false);
    }

    private Character getCharacter(Input input, Coordinate coordinate, Direction direction) {
        return input.find(coordinate.x() + direction.row, coordinate.y() +direction.column)
            .map(Coordinate::character)
            .orElse(' ');
    }

    private Optional<Coordinate> find(Input input, Coordinate coordinate, Direction direction, int distance) {
        return input.find(coordinate.x() + (direction.row * distance), coordinate.y() + (direction.column * distance));
    }

    @AllArgsConstructor
    private enum Direction {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        UP_LEFT(-1, -1),
        UP_RIGHT(-1, 1),
        DOWN_LEFT(1, -1),
        DOWN_RIGHT(1, 1),
        ;

        private final int row;
        private final int column;
    }
}
