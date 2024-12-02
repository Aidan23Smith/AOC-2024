package com.aoc2024.in.controller;

import com.aoc2024.api.model.AnswerRequest;
import com.aoc2024.api.model.Input;
import com.aoc2024.api.port.AnswerService;
import com.aoc2024.in.service.ReadInputService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

    private static final int DAY = 0;

    @Mock
    private AnswerService answerService;
    @Mock
    private ReadInputService readInputService;
    @InjectMocks
    private AnswerController underTest;

    @Mock
    private Input input;

    @Test
    void getAnswer_pt1() throws IOException {
        String expected = "expected";
        when(readInputService.getInput(DAY)).thenReturn(input);
        when(answerService.getPt1(new AnswerRequest(DAY, input))).thenReturn(expected);

        assertEquals(expected, underTest.getAnswer(DAY, 1));
    }

    @Test
    void getAnswer_pt2() throws IOException {
        String expected = "expected";
        when(readInputService.getInput(DAY)).thenReturn(input);
        when(answerService.getPt2(new AnswerRequest(DAY, input))).thenReturn(expected);

        assertEquals(expected, underTest.getAnswer(DAY, 2));
    }

}
