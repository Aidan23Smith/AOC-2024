package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
public class Day6 implements Day {

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(walk(input).size());
    }

    @Override
    public String getAnswer2(Input input) {
        Set<Pair<Integer, Integer>> walls = input.pairsWithCharacter('#');
        Pair<Integer, Integer> start = input.pairsWithCharacter('^').stream().findAny().orElseThrow();
        return String.valueOf(
            walk(input)
                .stream()
                .filter(newWall -> doesFindLoop(walls, start, newWall))
                .count()
        );
    }

    private Set<Pair<Integer, Integer>> walk(Input input) {
        Set<Pair<Integer, Integer>> walls = input.pairsWithCharacter('#');
        Direction direction = Direction.UP;
        Pair<Integer, Integer> current = input.pairsWithCharacter('^').stream().findAny().orElseThrow();
        Set<Pair<Integer, Integer>> totalWalk = new HashSet<>();

        Optional<Pair<Integer, Integer>> next = ahead(current, direction, input);
        while (next.isPresent()) {
            totalWalk.add(current);
            if (walls.contains(next.get())) {
                direction = direction.next();
                next = ahead(current, direction, input);
            }

            current = next.orElseThrow();
            next = ahead(current, direction, input);
        }
        totalWalk.add(current);

        return totalWalk;
    }

    private Optional<Pair<Integer, Integer>> ahead(Pair<Integer, Integer> current, Direction direction, Input input) {
        int row = current.getLeft() + direction.rowChange;
        int column = current.getRight() + direction.columnChange;

        if ((row < 0) || (row > input.numberOfRows()) || (column < 0) || (column > input.maxColumnInRow(0))) {
            return Optional.empty();
        }

        return Optional.of(Pair.of(row, column));
    }

    private boolean doesFindLoop(Set<Pair<Integer, Integer>> walls, Pair<Integer, Integer> start, Pair<Integer, Integer> nextWall) {
        walls = new HashSet<>(walls);
        Set<Triple<Integer, Integer, Direction>> totalWalk = new HashSet<>();

        walls.add(nextWall);

        Direction direction = Direction.UP;
        Pair<Integer, Integer> current = start;

        OptionalInt nextWallRowOrColumn = findNextWallRowOrColumn(walls, direction, current);

        while (nextWallRowOrColumn.isPresent()) {
            Pair<Integer, Integer> next = findNext(direction, current, nextWallRowOrColumn.getAsInt());
            if (totalWalk.contains(Triple.of(next.getLeft(), next.getRight(), direction))) {
                return true;
            }

            totalWalk.addAll(findPathWithDirection(direction, current, next));

            current = next;
            direction = direction.next();
            nextWallRowOrColumn = findNextWallRowOrColumn(walls, direction, current);
        }

        return false;
    }

    private OptionalInt findNextWallRowOrColumn(Set<Pair<Integer, Integer>> walls, Direction direction, Pair<Integer, Integer> current) {
        return switch (direction) {
            case UP -> walls.stream()
                .filter(wall -> wall.getLeft().equals(current.getLeft()))
                .mapToInt(Pair::getRight)
                .filter(row -> row < current.getRight())
                .max();
            case RIGHT -> walls.stream()
                .filter(wall -> wall.getRight().equals(current.getRight()))
                .mapToInt(Pair::getLeft)
                .filter(column -> column > current.getLeft())
                .min();
            case DOWN -> walls.stream()
                .filter(wall -> wall.getLeft().equals(current.getLeft()))
                .mapToInt(Pair::getRight)
                .filter(row -> row > current.getRight())
                .min();
            case LEFT -> walls.stream()
                .filter(wall -> wall.getRight().equals(current.getRight()))
                .mapToInt(Pair::getLeft)
                .filter(column -> column < current.getLeft())
                .max();
        };
    }

    private Pair<Integer, Integer> findNext(Direction direction, Pair<Integer, Integer> current, Integer nextRowOrColumn) {
        return switch (direction) {
            case UP -> Pair.of(current.getLeft(), nextRowOrColumn + 1);
            case DOWN -> Pair.of(current.getLeft(), nextRowOrColumn - 1);
            case RIGHT -> Pair.of(nextRowOrColumn - 1, current.getRight());
            case LEFT -> Pair.of(nextRowOrColumn + 1, current.getRight());
        };
    }

    private Set<Triple<Integer, Integer, Direction>> findPathWithDirection(Direction direction, Pair<Integer, Integer> current, Pair<Integer, Integer> next) {
        return switch (direction) {
            case UP, DOWN -> IntStream.range(Math.min(next.getRight(), current.getRight()), Math.max(next.getRight(), current.getRight()) + 1)
                .mapToObj(rowNum -> Triple.of(current.getLeft(), rowNum, direction))
                .collect(Collectors.toSet());
            case RIGHT, LEFT -> IntStream.range(Math.min(next.getLeft(), current.getLeft()), Math.max(next.getLeft(), current.getLeft()) + 1)
                .mapToObj(colNum -> Triple.of(colNum, current.getRight(), direction))
                .collect(Collectors.toSet());
        };
    }

    @AllArgsConstructor
    private enum Direction {
        UP(0, 0, -1),
        RIGHT(1, 1, 0),
        DOWN(2, 0, 1),
        LEFT(3, -1, 0);

        private final int direction;
        @Getter
        private final int rowChange;
        @Getter
        private final int columnChange;

        public Direction next() {
            int next = (direction + 1) % 4;
            return values()[next];
        }

    }

}
