package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public class Day9 implements Day {

    @Override
    public String getAnswer1(Input input) {
        List<Integer> diskMap = getDiskMap(input);
        List<Integer> blocksNumbers = convertToListFileNumbers(diskMap);
        List<Integer> optimised = optimisePt1(diskMap, blocksNumbers);
        return String.valueOf(findScore(optimised));
    }

    @Override
    public String getAnswer2(Input input) {
        List<Integer> diskMap = getDiskMap(input);
        List<Block> blockToSize = getBlocks(diskMap);
        optimisePt2(blockToSize);
        List<Integer> optimised = convertToList(blockToSize);
        return String.valueOf(findScore(optimised));
    }

    private List<Integer> optimisePt1(List<Integer> diskMap, List<Integer> blocksNumbers) {
        AtomicInteger currentStartIndex = new AtomicInteger(0);
        AtomicInteger currentEndIndex = new AtomicInteger(blocksNumbers.size() - 1);
        int currentBlock = 0;
        List<Integer> optimised = new ArrayList<>();
        while (optimised.size() < blocksNumbers.size()) {
            boolean isSpaceTaken = (currentBlock % 2) == 0;
            int quantityToAdd = Math.min(diskMap.get(currentBlock), Math.abs(optimised.size() - blocksNumbers.size()));
            optimised.addAll(IntStream.range(0, quantityToAdd)
                                 .mapToObj(i -> blocksNumbers.get(isSpaceTaken ? currentStartIndex.getAndIncrement() : currentEndIndex.getAndDecrement()))
                                 .toList());
            currentBlock++;
        }
        return optimised;
    }

    private List<Integer> convertToListFileNumbers(List<Integer> diskMap) {
        return IntStream.range(0, diskMap.size())
            .filter(index -> (index % 2) == 0)
            .flatMap(index -> IntStream.range(0, diskMap.get(index))
                .map(i -> index / 2))
            .boxed()
            .toList();
    }

    private Long findScore(List<Integer> optimised) {
        return IntStream.range(0, optimised.size())
            .mapToLong(index -> optimised.get(index) * index)
            .reduce(0L, Long::sum);
    }

    private List<Integer> convertToList(List<Block> blocks) {
        return blocks.stream()
            .flatMapToInt(block -> IntStream.range(0, block.size)
                .map(index -> block.fileName))
            .boxed()
            .toList();
    }

    private void optimisePt2(List<Block> blocks) {
        for (int currentFileToPlace = blocks.size() - 1; currentFileToPlace > 0; currentFileToPlace = currentFileToPlace - 1) {
            if (blocks.get(currentFileToPlace).isEmpty) {
                for (int currentGapToFill = 1; currentGapToFill < currentFileToPlace; currentGapToFill = currentGapToFill + 1) {
                    if (!blocks.get(currentGapToFill).isEmpty) {
                        if (blocks.get(currentGapToFill).size >= blocks.get(currentFileToPlace).size) {
                            blocks.set(currentGapToFill, new Block(blocks.get(currentGapToFill).fileName, blocks.get(currentGapToFill).size - blocks.get(currentFileToPlace).size, false));
                            blocks.add(currentGapToFill, blocks.get(currentFileToPlace));
                            blocks.set(currentFileToPlace + 1, new Block(0, blocks.get(currentFileToPlace + 1).size, false));
                            currentFileToPlace++;
                            break;
                        }
                    }
                }
            }
        }
    }

    private List<Block> getBlocks(List<Integer> diskMap) {
        AtomicInteger file = new AtomicInteger(0);
        return new ArrayList<>(IntStream.range(0, diskMap.size())
                                   .mapToObj(index -> new Block(((index % 2) == 0) ? file.getAndIncrement() : 0, diskMap.get(index), (index % 2) == 0))
                                   .toList());
    }

    private List<Integer> getDiskMap(Input input) {
        return input.getRowString(0).chars().mapToObj(a -> (char) a)
            .map(String::valueOf)
            .map(Integer::valueOf)
            .toList();
    }

    private record Block(Integer fileName, Integer size, Boolean isEmpty) {

    }

}
