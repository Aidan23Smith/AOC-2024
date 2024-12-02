package com.aoc2024.core.service;

import org.springframework.stereotype.Service;

@Service
public class MathsService {

    public long lcm(long num1, long num2) {
        long highest = Math.max(num1, num2);
        long lowest = Math.min(num1, num2);
        long lcm = highest;
        while ((lcm % lowest) != 0) {
            lcm += highest;
        }
        return lcm;
    }

}
