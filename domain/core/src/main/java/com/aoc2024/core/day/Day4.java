package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

import lombok.AllArgsConstructor;

@Service
public class Day4 implements Day {

    private static final String XMAS = "XMAS";

    @Override
    public String getAnswer1(Input input) {
        Set<Pair<Integer, Integer>> X = input.pairsWithCharacter(XMAS.charAt(0));
        Set<Pair<Integer, Integer>> M = input.pairsWithCharacter(XMAS.charAt(1));
        Set<Pair<Integer, Integer>> A = input.pairsWithCharacter(XMAS.charAt(2));
        Set<Pair<Integer, Integer>> S = input.pairsWithCharacter(XMAS.charAt(3));
        return String.valueOf(X.stream()
                                  .flatMap(xCoordinate ->
                                               Arrays.stream(Direction.values())
                                                   .filter(direction ->
                                                               M.contains(moveCoordinate(xCoordinate, direction, 1))
                                                               && A.contains(moveCoordinate(xCoordinate, direction, 2))
                                                               && S.contains(moveCoordinate(xCoordinate, direction, 3)))
                                  ).count());
    }

    @Override
    public String getAnswer2(Input input) {
        Set<Pair<Integer, Integer>> M = input.pairsWithCharacter(XMAS.charAt(1));
        Set<Pair<Integer, Integer>> A = input.pairsWithCharacter(XMAS.charAt(2));
        Set<Pair<Integer, Integer>> S = input.pairsWithCharacter(XMAS.charAt(3));
        return String.valueOf(A.stream()
                                  .filter(aCoordinate ->
                                              (M.contains(moveCoordinate(aCoordinate, Direction.UP_LEFT, 1))
                                               && S.contains(moveCoordinate(aCoordinate, Direction.DOWN_RIGHT, 1)))
                                              || (M.contains(moveCoordinate(aCoordinate, Direction.UP_RIGHT, 1))
                                                  && S.contains(moveCoordinate(aCoordinate, Direction.DOWN_LEFT, 1)))
                                              || (M.contains(moveCoordinate(aCoordinate, Direction.DOWN_RIGHT, 1))
                                                  && S.contains(moveCoordinate(aCoordinate, Direction.UP_LEFT, 1)))
                                              || (M.contains(moveCoordinate(aCoordinate, Direction.DOWN_LEFT, 1))
                                                  && S.contains(moveCoordinate(aCoordinate, Direction.UP_RIGHT, 1)))
                                  ).count());
    }

    private Pair<Integer, Integer> moveCoordinate(Pair<Integer, Integer> coordinate, Direction direction, int distance) {
        return Pair.of(coordinate.getLeft() + (direction.column * distance),
                       coordinate.getRight() + (direction.row * distance));
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
