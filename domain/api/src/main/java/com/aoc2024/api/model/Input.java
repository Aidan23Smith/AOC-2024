package com.aoc2024.api.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(fluent = true)
public class Input {

    @Singular
    private final Set<Coordinate> coordinates;

    public Set<Coordinate> getRow(int row) {
        return coordinates.stream()
            .filter(i -> i.y() == row)
            .collect(Collectors.toSet());
    }

    public String getRowString(int row) {
        return coordinates.stream()
            .filter(coordinate -> coordinate.y() == row)
            .sorted(Coordinate::compareTo)
            .map(Coordinate::character)
            .map(String::valueOf)
            .reduce("", (a, b) -> a + b);
    }

    public Set<String> getUnorderedRowStrings() {
        return IntStream.range(0, numberOfRows())
            .mapToObj(this::getRowString)
            .collect(Collectors.toSet());
    }

    public List<String> getRowStrings() {
        return IntStream.range(0, numberOfRows())
            .mapToObj(this::getRowString)
            .toList();
    }

    public List<String> getRowString(int row, String delimiter) {
        return Arrays.stream(getRowString(row).split(delimiter))
            .filter(s -> !s.isEmpty())
            .toList();
    }

    public Set<String> getUnorderedRows() {
        return IntStream.range(0, numberOfRows())
            .mapToObj(this::getRowString)
            .collect(Collectors.toSet());
    }

    public List<List<String>> getRowStrings(String delimiter) {
        return IntStream.range(0, numberOfRows())
            .mapToObj(rowNumber -> getRowString(rowNumber, delimiter))
            .toList();
    }

    public List<List<Long>> getRowNumbers(String delimiter) {
        return IntStream.range(0, numberOfRows())
            .mapToObj(rowNumber -> getRowString(rowNumber, delimiter))
            .map(numbers -> numbers.stream()
                .mapToLong(Long::valueOf)
                .boxed()
                .toList())
            .toList();
    }

    public Set<Coordinate> getColumn(int column) {
        return coordinates.stream()
            .filter(i -> i.x() == column)
            .collect(Collectors.toSet());
    }

    public Set<Coordinate> coordinatesWithCharacter(char character) {
        return coordinates.stream()
            .filter(i -> i.character() == character)
            .collect(Collectors.toSet());
    }

    public int maxColumnInRow(int row) {
        return getRow(row).stream()
            .map(Coordinate::x)
            .max(Integer::compareTo)
            .orElse(0);
    }

    public int numberOfRows() {
        return (int) coordinates.stream()
            .map(Coordinate::y)
            .distinct()
            .count();
    }

    public List<Coordinate> sorted() {
        return coordinates.stream()
            .sorted(Coordinate::compareTo)
            .collect(Collectors.toList());
    }

    public Coordinate get(int column, int row) {
        return coordinates.stream()
            .filter(coordinate -> coordinate.y() == row)
            .filter(coordinate -> coordinate.x() == column)
            .findFirst()
            .orElse(null);
    }

    public Set<Coordinate> between(int column1, int row1, int column2, int row2) {
        return coordinates.stream()
            .filter(coordinate -> coordinate.y() >= row1)
            .filter(coordinate -> coordinate.y() <= row2)
            .filter(coordinate -> coordinate.x() >= column1)
            .filter(coordinate -> coordinate.x() <= column2)
            .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return sorted().stream()
            .map(i -> {
                if ((i.x() == maxColumnInRow(i.y())) && (i.y() < (numberOfRows() - 1))) {
                    return i.character() + "\n";
                }
                return String.valueOf(i.character());
            })
            .reduce("", (a, b) -> a + b);
    }

}