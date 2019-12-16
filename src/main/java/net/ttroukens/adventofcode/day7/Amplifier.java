package net.ttroukens.adventofcode.day7;

import common.Projector;
import net.ttroukens.adventofcode.day5.GravityAdvanced;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Amplifier implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        return generateCombinations(Arrays.stream(firstLine.split(","))
                                          .map(Integer::parseInt)
                                          .toArray(Integer[]::new))
                .stream()
                .reduce(0, Integer::max);
    }

    private static ArrayList<Integer> generateCombinations(Integer... positions) {
        ArrayList<Integer> results = new ArrayList<>();
        for (Integer[] phases : generatePhases(0, 4)) {
            results.add(runAllAmplifiers(phases, positions));
        }
        return results;
    }

    static List<Integer[]> generatePhases(int start, int stop) {
        List<Integer[]> phases = new ArrayList<>();
        for (int one = start; one <= stop; one++) {
            for (int two = start; two <= stop; two++) {
                if (two != one) {
                    for (int three = start; three <= stop; three++) {
                        if (three != two && three != one) {
                            for (int four = start; four <= stop; four++) {
                                if (four != three && four != two && four != one) {
                                    for (int five = start; five <= stop; five++) {
                                        if (five != four && five != three && five != two && five != one) {
                                            phases.add(new Integer[]{one, two, three, four, five});
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return phases;
    }

    private static int runAllAmplifiers(Integer[] phases, Integer... positions) {
        Integer inputForTwo = runAmplifier(phases[0], 0, positions);
        Integer inputForThree = runAmplifier(phases[1], inputForTwo, positions);
        Integer inputForFour = runAmplifier(phases[2], inputForThree, positions);
        Integer inputForFive = runAmplifier(phases[3], inputForFour, positions);
        return runAmplifier(phases[4], inputForFive, positions);
    }

    private static int runAmplifier(Integer phaseSetting, Integer inputSignal, Integer... positions) {
        String input = phaseSetting + "\r\n" + inputSignal;
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        List<Integer> outputs = new ArrayList<>();
        GravityAdvanced.compute(scanner, outputs, positions.clone());
        return outputs.get(0);
    }
}
