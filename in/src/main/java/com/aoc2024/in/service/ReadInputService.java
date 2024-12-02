package com.aoc2024.in.service;

import com.aoc2024.api.model.Coordinate;
import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class ReadInputService {

    public Input getInput(int day) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(String.format("Day%s.txt", day));
        return readFromInputStream(inputStream);
    }

    private Input readFromInputStream(InputStream inputStream) throws IOException {
        Input.InputBuilder builder = Input.builder();
        int yPos = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                int xPos = 0;
                for (char character : line.toCharArray()) {
                    builder.coordinate(new Coordinate(xPos, yPos, character));
                    xPos += 1;
                }
                yPos += 1;
            }
        }
        return builder.build();
    }

}
