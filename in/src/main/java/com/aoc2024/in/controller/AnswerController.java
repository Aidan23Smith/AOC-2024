package com.aoc2024.in.controller;

import com.aoc2024.api.model.AnswerRequest;
import com.aoc2024.api.port.AnswerService;
import com.aoc2024.in.service.ReadInputService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AnswerController {

    private AnswerService answerService;
    private ReadInputService readInputService;

    @GetMapping("answer/{day}/pt/{part}")
    public String getAnswer(@PathVariable int day,
                            @PathVariable int part) throws IOException {
        if (part == 1) {
            return answerService.getPt1(new AnswerRequest(day, readInputService.getInput(day)));
        } else {
            return answerService.getPt2(new AnswerRequest(day, readInputService.getInput(day)));
        }
    }

}
