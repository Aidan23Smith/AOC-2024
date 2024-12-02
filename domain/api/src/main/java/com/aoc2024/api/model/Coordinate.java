package com.aoc2024.api.model;

import lombok.experimental.Accessors;

@Accessors(fluent = true)
public record Coordinate(int x, int y, char character) implements Comparable<Coordinate> {

    @Override
    public int compareTo(Coordinate other) {
        return (y == other.y) ? Integer.compare(x, other.x) : Integer.compare(y, other.y);
    }

}
