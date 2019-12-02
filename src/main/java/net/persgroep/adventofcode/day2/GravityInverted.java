package net.persgroep.adventofcode.day2;

import common.Projector;

import java.util.Arrays;
import java.util.stream.Stream;

public class GravityInverted implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        return nounAndVerbFor(Arrays.stream(firstLine.split(","))
                             .map(Integer::parseInt)
                             .toArray(Integer[]::new));
    }

    private static Integer nounAndVerbFor(Integer... positions) {
        int desiredResult = 19690720;

        for (int noun = 0; noun < 99; noun++) {
            for (int verb = 0; verb < 99; verb++) {
                if (Gravity.compute(setNounAndVerb(noun, verb, positions)) == desiredResult) {
                    return computeNounAndVerb(noun, verb);
                }
            }
        }

        return 0;
    }

    private static Integer[] setNounAndVerb(int noun, int verb, Integer... positions) {
        Integer[] newPositions = positions.clone();
        newPositions[1] = noun;
        newPositions[2] = verb;
        return newPositions;
    }

    private static Integer computeNounAndVerb(int noun, int verb) {
        return (100 * noun) + verb;
    }
}
