package com.aoc2024.api.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public List<String> getColumnStrings(String delimiter) {
        return getColumnValues(delimiter).stream()
            .map(column -> column.stream()
                .reduce("", (a, b) -> a + b))
            .toList();
    }

    public List<List<String>> getColumnValues(String delimiter) {
        List<List<String>> rows = getRowStrings(delimiter);
        return IntStream.range(0, rows.getFirst().size())
            .mapToObj(colNumber -> rows.stream()
                .map(row -> row.get(colNumber))
                .toList())
            .toList();
    }

    public List<List<Long>> getColumnNumbers(String delimiter) {
        return getColumnValues(delimiter).stream()
            .map(column -> column.stream()
                .map(Long::valueOf)
                .toList())
            .toList();
    }

    public Set<Coordinate> coordinatesWithCharacter(char character) {
        return coordinates.stream()
            .filter(i -> i.character() == character)
            .collect(Collectors.toSet());
    }

    public Set<Pair<Integer, Integer>> pairsWithCharacter(char character) {
        return coordinatesWithCharacter(character).stream()
            .map(coordinate -> Pair.of(coordinate.x(), coordinate.y()))
            .collect(Collectors.toSet());
    }

    public int maxColumnInRow(int row) {
        return getRow(row).stream()
            .map(Coordinate::x)
            .max(Integer::compareTo)
            .orElse(0);
    }

    public int numberOfRows() {
        return coordinates.stream()
            .map(Coordinate::y)
            .max(Integer::compareTo)
            .orElse(0);
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

    public Optional<Coordinate> find(int column, int row) {
        return coordinates.stream()
            .filter(coordinate -> coordinate.y() == row)
            .filter(coordinate -> coordinate.x() == column)
            .findFirst();
    }

    public List<Input> splitByBlankRow() {
        int missingRow = IntStream.range(0, numberOfRows())
            .filter(rowNum -> find(0, rowNum).isEmpty())
            .findAny()
            .orElseThrow();
        Input before = new Input(coordinates.stream()
                                     .filter(coordinate -> coordinate.y() < missingRow)
                                     .collect(Collectors.toSet()));
        Input after = new Input(coordinates.stream()
                                    .filter(coordinate -> coordinate.y() > missingRow)
                                    .map(coordinate -> new Coordinate(coordinate.x(), coordinate.y() - missingRow - 1, coordinate.character()))
                                    .collect(Collectors.toSet()));
        return List.of(before, after);
    }

    public Set<Coordinate> between(int column1, int row1, int column2, int row2) {
        return coordinates.stream()
            .filter(coordinate -> coordinate.y() >= row1)
            .filter(coordinate -> coordinate.y() <= row2)
            .filter(coordinate -> coordinate.x() >= column1)
            .filter(coordinate -> coordinate.x() <= column2)
            .collect(Collectors.toSet());
    }

    public Map<Character, Set<Pair<Integer, Integer>>> characterToPairsMap() {
        Set<Character> characters = getRowStrings().stream()
            .flatMap(row -> row.chars()
                .mapToObj(c -> (char) c))
            .collect(Collectors.toSet());

        return characters.stream()
            .collect(Collectors.toMap(character -> character, this::pairsWithCharacter));
    }

    public Map<Character, Set<Coordinate>> characterToCoordinateMap() {
        Set<Character> characters = getRowStrings().stream()
            .flatMap(row -> row.chars()
                .mapToObj(c -> (char) c))
            .collect(Collectors.toSet());

        return characters.stream()
            .collect(Collectors.toMap(character -> character, this::coordinatesWithCharacter));
    }

    public Map<Character, Set<Pair<Integer, Integer>>> characterToPairsMap(char... ignoring) {
        Set<Character> characters = getRowStrings().stream()
            .flatMap(row -> row.chars()
                .mapToObj(c -> (char) c))
            .filter(character -> !convertToList(ignoring).contains(character))
            .collect(Collectors.toSet());

        return characters.stream()
            .collect(Collectors.toMap(character -> character, this::pairsWithCharacter));
    }

    public Set<Coordinate> adjacentCoordinates(Coordinate coordinate) {
        return coordinates.stream()
            .filter(other -> coordinate.distanceFrom(other) == 1)
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

    private List<Character> convertToList(char... objects) {
        List<Character> list = new ArrayList<>();
        for (Character object : objects) {
            list.add(object);
        }
        return list;
    }

}
