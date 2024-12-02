package com.aoc2024.core.day;

import com.aoc2024.api.model.Coordinate;
import com.aoc2024.api.model.Input;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TestHelper {

    static Input convertToInput(String input) {
        Input.InputBuilder builder = Input.builder();
        int yPos = 0;
        String[] lines = input.split("\n");
        for (String line : lines) {
            int xPos = 0;
            for (char character : line.toCharArray()) {
                builder.coordinate(new Coordinate(xPos, yPos, character));
                xPos += 1;
            }
            yPos += 1;
        }
        return builder.build();
    }

}
