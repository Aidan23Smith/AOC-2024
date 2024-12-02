package com.aoc2024.core.usecase;

import com.aoc2024.api.model.AnswerRequest;
import com.aoc2024.api.model.Input;
import com.aoc2024.core.day.Day1;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    Day1 day1;
    @InjectMocks
    private AnswerServiceImpl underTest;

    @Test
    void getPt1_day1() {
        String expected = "answer";
        Input input = Input.builder().build();
        AnswerRequest request = new AnswerRequest(1, input);
        when(day1.getAnswer1(input)).thenReturn(expected);

        String actual = underTest.getPt1(request);

        assertEquals(expected, actual);
    }

    @Test
    void getPt1_notValidDay() {
        AnswerRequest request = new AnswerRequest(26, Input.builder().build());

        assertThrows(NotImplementedException.class, () -> underTest.getPt1(request));
    }

}
