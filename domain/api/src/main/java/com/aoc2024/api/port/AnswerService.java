package com.aoc2024.api.port;

import com.aoc2024.api.model.AnswerRequest;

public interface AnswerService {

    String getPt1(AnswerRequest answerRequest);

    String getPt2(AnswerRequest answerRequest);

}
