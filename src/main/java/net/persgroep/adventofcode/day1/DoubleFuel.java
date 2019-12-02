package net.persgroep.adventofcode.day1;

import common.Projector;

import java.util.stream.Stream;

public class DoubleFuel implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        return lines.map(Integer::parseInt)
                    .map(DoubleFuel::requirementsFor)
                    .reduce(0, Integer::sum);
    }

    private static Integer requirementsFor(Integer mass) {
        final int singleRequirements = Fuel.requirementFor(mass);
        if(singleRequirements <= 0) {
            return 0;
        }
        return singleRequirements + requirementsFor(singleRequirements);
    }

}
