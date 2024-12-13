package com.aoc2024.core.day;

import com.aoc2024.api.model.Input;
import com.aoc2024.core.service.RegexService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Day13 implements Day {

    private final RegexService regexService;

    @Override
    public String getAnswer1(Input input) {
        return String.valueOf(getMachines(input).stream()
                                  .map(this::calculateButtonPresses)
                                  .filter(this::filterByCap)
                                  .map(this::calculateToken)
                                  .reduce(0L, Long::sum));
    }

    @Override
    public String getAnswer2(Input input) {
        return String.valueOf(getMachines(input).stream()
                                  .map(this::displace)
                                  .map(this::calculateButtonPresses)
                                  .map(this::calculateToken)
                                  .reduce(0L, Long::sum));
    }

    private long calculateToken(XY presses) {
        return (presses.x * 3) + presses.y;
    }

    private boolean filterByCap(XY presses) {
        return (presses.x <= 100) && (presses.y <= 100);
    }

    private XY calculateButtonPresses(Machine machine) {
        double a = (double) ((machine.prize.x * machine.b.y) - (machine.prize.y * machine.b.x)) / ((machine.a.x * machine.b.y) - (machine.a.y * machine.b.x));
        double b = (double) ((machine.prize.x * machine.a.y) - (machine.prize.y * machine.a.x)) / ((machine.b.x * machine.a.y) - (machine.b.y * machine.a.x));

        if (((a % 1) == 0) && ((b % 1) == 0) && (a >= 0) && (b >= 0)) {
            return new XY((long) a, (long) b);
        }
        return new XY(0, 0);
    }

    private XY getNumbersForRow(String row) {
        List<String> aString = regexService.findPattern(row, "([0-9]+)");
        return new XY(Long.parseLong(aString.getFirst()),
                      Long.parseLong(aString.getLast()));
    }

    private Machine displace(Machine machine) {
        long displacement = 10000000000000L;

        return new Machine(machine.a,
                           machine.b,
                           new XY(machine.prize.x + displacement, machine.prize.y + displacement));
    }

    private Set<Machine> getMachines(Input input) {
        return IntStream.range(0, (input.numberOfRows() / 4) + 1)
            .mapToObj(row -> new Machine(getNumbersForRow(input.getRowString(row * 4)),
                                         getNumbersForRow(input.getRowString((row * 4) + 1)),
                                         getNumbersForRow(input.getRowString((row * 4) + 2))))
            .collect(Collectors.toSet());
    }

    private record Machine(XY a, XY b, XY prize) {

    }

    private record XY(long x, long y) {

    }

}
