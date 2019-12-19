package net.ttroukens.adventofcode.day9;

import common.Projector;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RevisitingGravity implements Projector<BigInteger> {

    @Override
    public BigInteger project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        return compute(Arrays.stream(firstLine.split(","))
                             .map(BigInteger::new)
                             .collect(Collectors.toList()));
    }

    private static BigInteger compute(List<BigInteger> positions) {
        Scanner input = new Scanner(System.in);
        List<BigInteger> outputs = new ArrayList<>();
        List<BigInteger> inputs = new ArrayList<>(positions);
        for (int i = inputs.size(); i < positions.size()*10; i++) {
            inputs.add(BigInteger.ZERO);
        }
        return compute(input, outputs, inputs);
    }

    private static BigInteger compute(Scanner input, List<BigInteger> outputs, List<BigInteger> positions) {
        int currentPosition = 0;
        long currentOperation = 0;
        BigInteger currentRelativeBase = BigInteger.ZERO;

        while (currentOperation != 99) {
            LinkedList<Integer> opcode = retrieveOpcode(positions.get(currentPosition).intValue());
            currentOperation = concat(opcode.pollFirst(), opcode.pollFirst());
            if (currentOperation == 1) {
                BigInteger valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                BigInteger valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
                BigInteger sum = valueA.add(valueB);
                setValue(opcode, currentPosition + 3, sum, currentRelativeBase, positions);
                currentPosition += 4;
            } else if (currentOperation == 2) {
                BigInteger valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                BigInteger valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
                BigInteger multiplied = valueA.multiply(valueB);
                setValue(opcode, currentPosition + 3, multiplied, currentRelativeBase, positions);
                currentPosition += 4;
            } else if (currentOperation == 3) {
                System.out.print("Input: ");
                BigInteger value = input.nextBigInteger();
                System.out.println();
                setValue(opcode, currentPosition + 1, value, currentRelativeBase, positions);
                currentPosition += 2;
            } else if (currentOperation == 4) {
                BigInteger value = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                System.out.println("Value: " + value);
                outputs.add(value);
                currentPosition += 2;
            } else if (currentOperation == 5) {
                BigInteger valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                if (valueA.intValue() != 0) {
                    BigInteger value = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
                    currentPosition = value.intValue();
                } else {
                    currentPosition += 3;
                }
            } else if (currentOperation == 6) {
                BigInteger valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                if (valueA.intValue() == 0) {
                    BigInteger value = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
                    currentPosition = value.intValue();
                } else {
                    currentPosition += 3;
                }
            } else if (currentOperation == 7) {
                BigInteger valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                BigInteger valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
                BigInteger result = (valueA.compareTo(valueB) < 0) ? BigInteger.ONE : BigInteger.ZERO;
                setValue(opcode, currentPosition + 3, result, currentRelativeBase, positions);
                currentPosition += 4;
            } else if (currentOperation == 8) {
                BigInteger valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                BigInteger valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
                BigInteger result = (valueA.equals(valueB)) ? BigInteger.ONE : BigInteger.ZERO;
                setValue(opcode, currentPosition + 3, result, currentRelativeBase, positions);
                currentPosition += 4;
            } else if (currentOperation == 9) {
                BigInteger value = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
                currentRelativeBase = currentRelativeBase.add(value);
                currentPosition += 2;
            } else if (currentOperation != 99) {
                System.out.println("oops: " + currentOperation);
            }
        }

        return positions.get(0);
    }

    private static LinkedList<Integer> retrieveOpcode(Integer current) {
        LinkedList<Integer> opcode = new LinkedList<>();
        while (current > 0) {
            opcode.add(current % 10);
            current = current / 10;
        }
        return opcode;
    }

    private static Integer concat(Integer int2, Integer int1) {
        //coming in in the wrong order (last one first), so turning both integers here
        String string1 = toInteger(int1);
        String string2 = toInteger(int2);
        String result = string1 + string2;
        return Integer.parseInt(result);
    }

    private static String toInteger(Integer number) {
        return (number == null) ? "0" : Integer.toString(number);
    }

    private static BigInteger retrieveValue(LinkedList<Integer> opcode, int positionToRetrieveFrom, BigInteger relativeBase, List<BigInteger> positions) {
        Integer mode = opcode.pollFirst();
        if (mode == null || mode == 0) { //position mode
            int position = positions.get(positionToRetrieveFrom).intValue();
            return positions.get(position);
        } else if (mode == 1) { //immediate mode
            return positions.get(positionToRetrieveFrom);
        } else { //relative mode (mode = 2)
            int position = (positions.get(positionToRetrieveFrom).add(relativeBase)).intValue();
            return positions.get(position);
        }
    }

    private static void setValue(LinkedList<Integer> opcode, int positionToSetValueTo, BigInteger value, BigInteger relativeBase, List<BigInteger> positions) {
        Integer mode = opcode.pollFirst();
        if (mode == null || mode == 0) { //position mode
            int position = positions.get(positionToSetValueTo).intValue();
            positions.set(position, value);
        } else if (mode == 1) { //immediate mode
            positions.set(positionToSetValueTo, value);
        } else { //relative mode (mode = 2)
            int position = (positions.get(positionToSetValueTo).add(relativeBase)).intValue();
            positions.set(position, value);
        }
    }
}
