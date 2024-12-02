package com.aoc2024.core.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Service
public class RegexService {

    public List<String> findPattern(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        List<String> found = new ArrayList<>();
        while (matcher.find()) {
            found.addAll(IntStream.range(1, matcher.groupCount() + 1)
                             .mapToObj(matcher::group)
                             .toList());
        }
        return found;
    }

}
