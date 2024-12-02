package com.aoc2024.core.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MathsServiceTest {

    @InjectMocks
    MathsService mathsService;

    @Nested
    class LCM {

        @Test
        void lcm_0_in_input() {
            long num1 = 0;
            long num2 = 1;

            assertThrows(ArithmeticException.class, () -> mathsService.lcm(num1, num2));
        }

        @Test
        void lcm_same_input() {
            long num1 = 2;
            long num2 = 2;

            long actual = mathsService.lcm(num1, num2);

            assertEquals(2, actual);
        }

        @Test
        void lcm_different_input() {
            long num1 = 5;
            long num2 = 3;

            long actual = mathsService.lcm(num1, num2);

            assertEquals(15, actual);
        }

    }

}
