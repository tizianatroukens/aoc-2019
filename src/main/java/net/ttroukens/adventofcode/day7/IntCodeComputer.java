package net.ttroukens.adventofcode.day7;

import net.ttroukens.adventofcode.day5.GravityAdvanced;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IntCodeComputer {
    enum ExitReason {
        NEED_INPUT,
        EXITED
    }

    private Integer[] originalPositions;
    private Integer[] positions;
    private LinkedList<Integer> inputs = new LinkedList<>();
    private LinkedList<Integer> outputs = new LinkedList<>();
    private Integer maxOutput = Integer.MIN_VALUE;
    private ExitReason lastExitReason;

    private int currentPosition = 0;

    IntCodeComputer(Integer phaseSetting, Integer initialInput, Integer... originalPositions) {
        inputs.add(phaseSetting);
        inputs.add(initialInput);
        this.originalPositions = originalPositions;
    }

    IntCodeComputer(Integer phaseSetting, Integer... originalPositions) {
        inputs.add(phaseSetting);
        this.originalPositions = originalPositions;
    }

    void runUntilBlockedOrExited() {
        lastExitReason = compute();
    }

    void addInputs(List<Integer> inputs) {
        this.inputs.addAll(inputs);
    }

    Integer numberOfInputs() {
        return inputs.size();
    }

    List<Integer> getOutputs() {
        List<Integer> outputs = new ArrayList<>();
        while (!this.outputs.isEmpty()) {
            outputs.add(this.outputs.pop());
        }
        return outputs;
    }

    Integer getMaxOutput() {
        return maxOutput;
    }

    ExitReason getLastExitReason() {
        return this.lastExitReason;
    }

    private ExitReason compute() {
        this.positions = originalPositions.clone();
        while (true) {
            LinkedList<Integer> opcode = GravityAdvanced.retrieveOpcode(positions[currentPosition]);
            int currentOperation = GravityAdvanced.concat(opcode.pollFirst(), opcode.pollFirst());
            if (currentOperation == 1) {
                sum(opcode);
            } else if (currentOperation == 2) {
                multiply(opcode);
            } else if (currentOperation == 3) {
                if (inputs.isEmpty()) {
                    return ExitReason.NEED_INPUT;
                }
                readInput(opcode, inputs.pop());
            } else if (currentOperation == 4) {
                processOutput(opcode);
            } else if (currentOperation == 5) {
                jumpIfTrue(opcode);
            } else if (currentOperation == 6) {
                jumpIfFalse(opcode);
            } else if (currentOperation == 7) {
                lessThan(opcode);
            } else if (currentOperation == 8) {
                equals(opcode);
            } else if (currentOperation == 99) {
                return ExitReason.EXITED;
            } else {
                throw new RuntimeException("hum: " + currentOperation);
            }
        }
    }

    private void sum(LinkedList<Integer> opcode) {
        Integer valueA = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        Integer valueB = GravityAdvanced.retrieveValue(opcode, currentPosition + 2, positions);
        Integer positionOfResult = positions[currentPosition + 3];
        Integer sum = valueA + valueB;
        positions[positionOfResult] = sum;
        currentPosition += 4;
    }

    private void multiply(LinkedList<Integer> opcode) {
        Integer valueA = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        Integer valueB = GravityAdvanced.retrieveValue(opcode, currentPosition + 2, positions);
        Integer positionOfResult = positions[currentPosition + 3];
        Integer multiplied = valueA * valueB;
        positions[positionOfResult] = multiplied;
        currentPosition += 4;
    }

    private void readInput(LinkedList<Integer> opcode, Integer input) {
        GravityAdvanced.setValue(opcode, currentPosition + 1, input, positions);
        currentPosition += 2;
    }

    private void processOutput(LinkedList<Integer> opcode) {
        Integer value = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        outputs.add(value);
        if (value > maxOutput) {
            maxOutput = value;
        }
        currentPosition += 2;
    }

    private void jumpIfTrue(LinkedList<Integer> opcode) {
        Integer valueA = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        if (valueA != 0) {
            currentPosition = GravityAdvanced.retrieveValue(opcode, currentPosition + 2, positions);
        } else {
            currentPosition += 3;
        }
    }

    private void jumpIfFalse(LinkedList<Integer> opcode) {
        Integer valueA = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        if (valueA == 0) {
            currentPosition = GravityAdvanced.retrieveValue(opcode, currentPosition + 2, positions);
        } else {
            currentPosition += 3;
        }
    }

    private void lessThan(LinkedList<Integer> opcode) {
        Integer valueA = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        Integer valueB = GravityAdvanced.retrieveValue(opcode, currentPosition + 2, positions);
        Integer positionOfResult = positions[currentPosition + 3];
        positions[positionOfResult] = (valueA < valueB) ? 1 : 0;
        currentPosition += 4;
    }

    private void equals(LinkedList<Integer> opcode) {
        Integer valueA = GravityAdvanced.retrieveValue(opcode, currentPosition + 1, positions);
        Integer valueB = GravityAdvanced.retrieveValue(opcode, currentPosition + 2, positions);
        Integer positionOfResult = positions[currentPosition + 3];
        positions[positionOfResult] = (valueA.equals(valueB)) ? 1 : 0;
        currentPosition += 4;
    }
}
