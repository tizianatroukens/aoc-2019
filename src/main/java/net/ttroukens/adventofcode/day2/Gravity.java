package net.ttroukens.adventofcode.day2;

import common.Projector;

import java.util.Arrays;
import java.util.stream.Stream;

public class Gravity implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        return compute(Arrays.stream(firstLine.split(","))
                             .map(Integer::parseInt)
                             .toArray(Integer[]::new));
    }

    static int compute(Integer... positions) {
        int currentPosition = 0;
        while (positions[currentPosition] != 99) {
            if (positions[currentPosition] == 1) {
                // add values
                Integer positionOfA = positions[currentPosition + 1];
                Integer positionOfB = positions[currentPosition + 2];
                Integer positionOfResult = positions[currentPosition + 3];
                Integer sum = positions[positionOfA] + positions[positionOfB];
                positions[positionOfResult] = sum;
            } else if (positions[currentPosition] == 2) {
                // multiply
                Integer positionOfA = positions[currentPosition + 1];
                Integer positionOfB = positions[currentPosition + 2];
                Integer positionOfResult = positions[currentPosition + 3];
                Integer multiplied = positions[positionOfA] * positions[positionOfB];
                positions[positionOfResult] = multiplied;
            }

            currentPosition += 4;
        }

        return positions[0];
    }
}
