package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Day11 implements Day {

    @Override
    public String getAnswer1(Input input) {
        Map<Long, Long> stonesToCount = getInitialStones(input);
        return String.valueOf(stonesAfterBlinking$Times(stonesToCount, 25));
    }

    @Override
    public String getAnswer2(Input input) {
        Map<Long, Long> stonesToCount = getInitialStones(input);
        return String.valueOf(stonesAfterBlinking$Times(stonesToCount, 75));
    }

    private Map<Long, Long> getInitialStones(Input input) {
        Map<Long, Long> initialStones = new HashMap<>();
        input.getRowNumbers(" ").getFirst()
            .forEach(stone -> {
                if (initialStones.containsKey(stone)) {
                    initialStones.put(stone, initialStones.get(stone) + 1);
                } else {
                    initialStones.put(stone, 1L);
                }
            });
        return initialStones;
    }

    private long stonesAfterBlinking$Times(Map<Long, Long> stonesToCount, int numberOfBlinks) {
        for (int blinkCount = 0; blinkCount < numberOfBlinks; blinkCount++) {
            stonesToCount = blink(stonesToCount);
        }
        return stonesToCount.values().stream().mapToLong(Long::longValue).sum();
    }

    private Map<Long, Long> blink(Map<Long, Long> stonesToCount) {
        Map<Long, Long> newStonesToCount = new HashMap<>();
        stonesToCount.forEach((key, value) -> {
            List<Long> newStones = applyRules(key);
            newStones.forEach(newStone -> {
                if (newStonesToCount.containsKey(newStone)) {
                    newStonesToCount.put(newStone, newStonesToCount.get(newStone) + value);
                } else {
                    newStonesToCount.put(newStone, value);
                }
            });
        });
        return newStonesToCount;
    }

    private List<Long> applyRules(Long stoneNum) {
        if (stoneNum == 0) {
            return rule1();
        }
        if ((String.valueOf(stoneNum).length() % 2) == 0) {
            return rule2(stoneNum);
        }
        return rule3(stoneNum);
    }

    private List<Long> rule1() {
        return List.of(1L);
    }

    private List<Long> rule2(Long stoneNum) {
        String stringOfStoneNum = String.valueOf(stoneNum);
        int halfWayIndex = stringOfStoneNum.length() / 2;
        return List.of(Long.parseLong(stringOfStoneNum.substring(0, halfWayIndex)), Long.parseLong(stringOfStoneNum.substring(halfWayIndex)));
    }

    private List<Long> rule3(Long stoneNum) {
        return List.of(stoneNum * 2024);
    }

}
