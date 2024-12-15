package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;
import com.aoc2024.core.config.RowColumnConfig;
import com.aoc2024.core.service.RegexService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Day14 implements Day {

    private final RegexService regexService;
    private final RowColumnConfig.RowColumn rowColumnDay14;

    @Override
    public String getAnswer1(Input input) {
        List<XY> finalPositions = convertToRobots(input).stream()
            .map(robot -> moveRobots(robot, 100))
            .map(robot -> robot.position)
            .toList();

        return String.valueOf(multiplyQuadrants(finalPositions));
    }

    @Override
    public String getAnswer2(Input input) {
        Set<Robot> robots = convertToRobots(input);

        int secondsPassed = 0;
        while (robots.stream().map(robot -> robot.position).collect(Collectors.toSet()).size() != robots.size()) {
            robots = robots.stream().map(robot -> moveRobots(robot, 1)).collect(Collectors.toSet());
            secondsPassed++;
        }

        System.out.println("After " + secondsPassed + " seconds:");
        printRobots(robots);

        return String.valueOf(secondsPassed);
    }

    private void printRobots(Set<Robot> robots) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, rowColumnDay14.rowCount())
            .forEach(rowNum -> {
                Set<Integer> columnsWithRobot = robots.stream()
                    .map(robot -> robot.position)
                    .filter(robot -> robot.y == rowNum)
                    .map(robot -> robot.x)
                    .collect(Collectors.toSet());
                IntStream.range(0, rowColumnDay14.columnCount())
                    .forEach(columnNum -> {
                        if (columnsWithRobot.contains(columnNum)) {
                            sb.append('#');
                        } else {
                            sb.append(' ');
                        }
                    });
                sb.append('\n');
            });
        System.out.println(sb);
    }

    private long multiplyQuadrants(List<XY> robotPositions) {
        int midX = (rowColumnDay14.columnCount() - 1) / 2;
        int midY = (rowColumnDay14.rowCount() - 1) / 2;

        long topLeft = robotPositions.stream()
            .filter(robot -> robot.x < midX)
            .filter(robot -> robot.y < midY)
            .count();
        long topRight = robotPositions.stream()
            .filter(robot -> robot.x > midX)
            .filter(robot -> robot.y < midY)
            .count();
        long bottomLeft = robotPositions.stream()
            .filter(robot -> robot.x < midX)
            .filter(robot -> robot.y > midY)
            .count();
        long bottomRight = robotPositions.stream()
            .filter(robot -> robot.x > midX)
            .filter(robot -> robot.y > midY)
            .count();

        return topLeft * topRight * bottomLeft * bottomRight;
    }

    private Robot moveRobots(Robot robot, int seconds) {
        return new Robot(new XY(bringToRange(robot.position.x + (robot.velocity.x * seconds), rowColumnDay14.columnCount()),
                                bringToRange(robot.position.y + (robot.velocity.y * seconds), rowColumnDay14.rowCount())),
                         robot.velocity);
    }

    private int bringToRange(int coord, int max) {
        return ((coord % max) + max) % max;
    }

    private Set<Robot> convertToRobots(Input input) {
        return input.getRowStrings().stream()
            .map(row -> regexService.findPattern(row, "([0-9-]+)"))
            .map(rowNumbers -> new Robot(
                new XY(Integer.parseInt(rowNumbers.getFirst()), Integer.parseInt(rowNumbers.get(1))),
                new XY(Integer.parseInt(rowNumbers.get(2)), Integer.parseInt(rowNumbers.getLast()))))
            .collect(Collectors.toSet());
    }

    private record Robot(XY position, XY velocity) {

    }

    private record XY(int x, int y) {

    }

}
