package net.ttroukens.adventofcode.day5;

import common.Projector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class GravityAdvanced implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        return compute(Arrays.stream(firstLine.split(","))
                             .map(Integer::parseInt)
                             .toArray(Integer[]::new));
    }

    private static int compute(Integer... positions) {
        Scanner input = new Scanner(System.in);
        List<Integer> outputs = new ArrayList<>();
        return compute(input, outputs, positions);
    }

    public static int compute(Scanner input, List<Integer> outputs, Integer... positions) {
        int currentPosition = 0, currentOperation = 0;

        while (currentOperation != 99) {
            LinkedList<Integer> opcode = retrieveOpcode(positions[currentPosition]);
            currentOperation = concat(opcode.pollFirst(), opcode.pollFirst());
            if (currentOperation == 1) {
                Integer valueA = retrieveValue(opcode, currentPosition + 1, positions);
                Integer valueB = retrieveValue(opcode, currentPosition + 2, positions);
                Integer positionOfResult = positions[currentPosition + 3];
                Integer sum = valueA + valueB;
                positions[positionOfResult] = sum;
                currentPosition += 4;
            } else if (currentOperation == 2) {
                Integer valueA = retrieveValue(opcode, currentPosition + 1, positions);
                Integer valueB = retrieveValue(opcode, currentPosition + 2, positions);
                Integer positionOfResult = positions[currentPosition + 3];
                Integer multiplied = valueA * valueB;
                positions[positionOfResult] = multiplied;
                currentPosition += 4;
            } else if (currentOperation == 3) {
                System.out.print("Input: ");
                Integer value = input.nextInt();
                System.out.println();
                setValue(opcode, currentPosition + 1, value, positions);
                currentPosition += 2;
            } else if (currentOperation == 4) {
                Integer value = retrieveValue(opcode, currentPosition + 1, positions);
                System.out.println("Value: " + value);
                outputs.add(value);
                currentPosition += 2;
            } else if (currentOperation == 5) {
                Integer valueA = retrieveValue(opcode, currentPosition + 1, positions);
                if (valueA != 0) {
                    currentPosition = retrieveValue(opcode, currentPosition + 2, positions);
                } else {
                    currentPosition += 3;
                }
            } else if (currentOperation == 6) {
                Integer valueA = retrieveValue(opcode, currentPosition + 1, positions);
                if (valueA == 0) {
                    currentPosition = retrieveValue(opcode, currentPosition + 2, positions);
                } else {
                    currentPosition += 3;
                }
            } else if (currentOperation == 7) {
                Integer valueA = retrieveValue(opcode, currentPosition + 1, positions);
                Integer valueB = retrieveValue(opcode, currentPosition + 2, positions);
                Integer positionOfResult = positions[currentPosition + 3];
                positions[positionOfResult] = (valueA < valueB) ? 1 : 0;
                currentPosition += 4;
            } else if (currentOperation == 8) {
                Integer valueA = retrieveValue(opcode, currentPosition + 1, positions);
                Integer valueB = retrieveValue(opcode, currentPosition + 2, positions);
                Integer positionOfResult = positions[currentPosition + 3];
                positions[positionOfResult] = (valueA.equals(valueB)) ? 1 : 0;
                currentPosition += 4;
            } else if (currentOperation != 99) {
                System.out.println("oops: " + currentOperation);
            }
        }

        return positions[0];
    }

    public static LinkedList<Integer> retrieveOpcode(int current) {
        LinkedList<Integer> opcode = new LinkedList<>();
        while (current > 0) {
            opcode.add(current % 10);
            current = current / 10;
        }
        return opcode;
    }

    public static Integer concat(Integer int2, Integer int1) {
        //coming in in the wrong order (last one first), so turning both integers here
        String string1 = toInteger(int1);
        String string2 = toInteger(int2);
        String result = string1 + string2;
        return Integer.parseInt(result);
    }

    private static String toInteger(Integer integer) {
        return (integer == null) ? "0" : Integer.toString(integer);
    }

    public static Integer retrieveValue(LinkedList<Integer> opcode, Integer positionToRetrieveFrom, Integer... positions) {
        Integer mode = opcode.pollFirst();
        if (mode == null || mode == 0) {
            int position = positions[positionToRetrieveFrom];
            return positions[position];
        } else {
            return positions[positionToRetrieveFrom];
        }
    }

    public static void setValue(LinkedList<Integer> opcode, Integer positionToSetValueTo, Integer value, Integer... positions) {
        Integer mode = opcode.pollFirst();
        if (mode == null || mode == 0) {
            int position = positions[positionToSetValueTo];
            positions[position] = value;
        } else {
            positions[positionToSetValueTo] = value;
        }
    }
}
