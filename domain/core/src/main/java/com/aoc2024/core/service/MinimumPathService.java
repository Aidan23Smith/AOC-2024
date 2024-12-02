package com.aoc2024.core.service;

import com.aoc2024.api.model.Coordinate;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class MinimumPathService {

    private final Map<Coordinate, Map<Coordinate, Long>> neighbourMap = new HashMap<>();

    public void addNeighbours(Coordinate coordinate1, Coordinate coordinate2) {
        addNeighbourToCoordinate(coordinate1, coordinate2);
        addNeighbourToCoordinate(coordinate2, coordinate1);
    }

    public void addNeighbours(Coordinate coordinate1, Coordinate coordinate2, long distance) {
        addNeighbourToCoordinate(coordinate1, coordinate2, distance);
        addNeighbourToCoordinate(coordinate2, coordinate1, distance);
    }

    public void addNeighbourToCoordinate(Coordinate coordinate, Coordinate neighbour) {
        addNeighbourToCoordinate(coordinate, neighbour, 1);
    }

    public void addNeighbourToCoordinate(Coordinate coordinate, Coordinate neighbour, long distance) {
        Map<Coordinate, Long> updatedNeighbours;
        if (!neighbourMap.containsKey(coordinate)) {
            updatedNeighbours = new HashMap<>();
        } else {
            updatedNeighbours = neighbourMap.get(coordinate);
        }
        updatedNeighbours.put(neighbour, distance);
        neighbourMap.put(coordinate, updatedNeighbours);
    }

    public List<Coordinate> getMinimumPath(Coordinate start, Coordinate end) {
        Set<Triple<Coordinate, Long, Coordinate>> closestNeighbours = getMinimumPathCoordinates(start);
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;

        while (current != start) {
            path.add(current);
            current = findCurrentWithPrevious(current, closestNeighbours).getRight();
        }
        path.add(start);

        return path.reversed();
    }

    public Long getMinimumLength(Coordinate start, Coordinate end) {
        Set<Triple<Coordinate, Long, Coordinate>> closestNeighbours = getMinimumPathCoordinates(start);
        return findCurrentWithPrevious(end, closestNeighbours).getMiddle();
    }

    private Triple<Coordinate, Long, Coordinate> findCurrentWithPrevious(Coordinate neighbour, Set<Triple<Coordinate, Long, Coordinate>> closestNeighbours) {
        return closestNeighbours.stream()
            .filter(n -> n.getLeft().equals(neighbour))
            .findFirst()
            .orElseThrow();
    }

    private Set<Triple<Coordinate, Long, Coordinate>> getMinimumPathCoordinates(Coordinate start) {
        AbstractQueue<Triple<Coordinate, Long, Coordinate>> unvisitedQueue = new PriorityQueue<>(Comparator.comparingLong(Triple::getMiddle));
        Set<Triple<Coordinate, Long, Coordinate>> visitedDistanceAndPrevious = new HashSet<>();
        Set<Coordinate> visited = new HashSet<>();

        unvisitedQueue.add(new ImmutableTriple<>(start, 0L, null));

        Optional<Triple<Coordinate, Long, Coordinate>> closest = findClosestNotVisited(unvisitedQueue, visited);
        while (closest.isPresent()) {
            unvisitedQueue.addAll(unvisitedNeighbours(closest.get(), visited));
            visited.add(closest.get().getLeft());
            visitedDistanceAndPrevious.add(closest.get());
            closest = findClosestNotVisited(unvisitedQueue, visited);
        }

        return visitedDistanceAndPrevious;
    }

    private Optional<Triple<Coordinate, Long, Coordinate>> findClosestNotVisited(AbstractQueue<Triple<Coordinate, Long, Coordinate>> unvisitedQueue, Set<Coordinate> visited) {
        Triple<Coordinate, Long, Coordinate> closest = unvisitedQueue.remove();
        while (visited.contains(closest.getLeft())) {
            if (unvisitedQueue.isEmpty()) {
                return Optional.empty();
            }
            closest = unvisitedQueue.remove();
        }
        return Optional.of(closest);
    }

    private Set<Triple<Coordinate, Long, Coordinate>> unvisitedNeighbours(Triple<Coordinate, Long, Coordinate> current, Set<Coordinate> visited) {
        Map<Coordinate, Long> neighbours = new HashMap<>(neighbourMap.get(current.getLeft()));
        return neighbours.entrySet().stream()
            .filter(neighbour -> !visited.contains(neighbour.getKey()))
            .map(neighbour -> new ImmutableTriple<>(neighbour.getKey(), current.getMiddle() + neighbour.getValue(), current.getLeft()))
            .collect(Collectors.toSet());
    }

}
