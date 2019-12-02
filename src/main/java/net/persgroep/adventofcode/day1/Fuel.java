package net.persgroep.adventofcode.day1;

import common.Projector;

import java.util.stream.Stream;

public class Fuel implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        return lines.map(Integer::parseInt)
                    .map(Fuel::requirementFor)
                    .reduce(Integer::sum)
                    .orElse(0);
    }

    static int requirementFor(Integer mass) {
        return (mass / 3) - 2;
    }
}
