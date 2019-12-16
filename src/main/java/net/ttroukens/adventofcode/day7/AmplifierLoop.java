package net.ttroukens.adventofcode.day7;

import common.Projector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AmplifierLoop implements Projector<Integer> {

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
        for (Integer[] phases : Amplifier.generatePhases(5, 9)) {
            results.add(runAllAmplifiers(phases, positions));
        }
        return results;
    }

    private static int runAllAmplifiers(Integer[] phases, Integer... positions) {
        IntCodeComputer intCodeComputer1 = new IntCodeComputer(phases[0], 0, positions.clone());
        IntCodeComputer intCodeComputer2 = new IntCodeComputer(phases[1], positions.clone());
        IntCodeComputer intCodeComputer3 = new IntCodeComputer(phases[2], positions.clone());
        IntCodeComputer intCodeComputer4 = new IntCodeComputer(phases[3], positions.clone());
        IntCodeComputer intCodeComputer5 = new IntCodeComputer(phases[4], positions.clone());
        List<IntCodeComputer> computers = Arrays.asList(intCodeComputer1, intCodeComputer2, intCodeComputer3, intCodeComputer4, intCodeComputer5);

        int computerToRun = 0;
        do {
            IntCodeComputer computer = computers.get(computerToRun);
            computer.runUntilBlockedOrExited();

            computerToRun = (computerToRun + 1) % computers.size();
            computers.get(computerToRun).addInputs(computer.getOutputs());
        } while (
            shouldKeepOnRunning(computers.get(computerToRun).numberOfInputs(),
                                computers.get(computerToRun).getLastExitReason())
        );

        return computers.get(computers.size() - 1).getMaxOutput();
    }

    private static boolean shouldKeepOnRunning(int numberOfInputs, IntCodeComputer.ExitReason exitReason) {
        return !(numberOfInputs == 0 || exitReason == IntCodeComputer.ExitReason.EXITED);
    }
}
