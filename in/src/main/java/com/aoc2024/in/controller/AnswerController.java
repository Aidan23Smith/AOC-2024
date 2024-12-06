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
        long start = System.currentTimeMillis();
        String answer;
        if (part == 1) {
            answer = answerService.getPt1(new AnswerRequest(day, readInputService.getInput(day)));
        } else {
            answer = answerService.getPt2(new AnswerRequest(day, readInputService.getInput(day)));
        }
        long finish = System.currentTimeMillis();
        System.out.println("total runtime: "  + (finish - start));
        return answer;
    }

}
