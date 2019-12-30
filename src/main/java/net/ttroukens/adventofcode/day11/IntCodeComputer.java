package net.ttroukens.adventofcode.day11;

import java.awt.*;
import java.util.*;
import java.util.List;

public class IntCodeComputer {
    private int currentPosition = 0;
    private Long currentRelativeBase = 0L;

    private List<Long> positions;
    private LinkedList<Long> outputs = new LinkedList<>();

    private Map<Coordinate, Color> coordinates;
    private Direction currentDirection = Direction.UP;
    private Coordinate currentCoordinate = new Coordinate(0, 0);

    public IntCodeComputer(List<Long> positions) {
        this.positions = new ArrayList<>(positions);
        for (int i = positions.size(); i < positions.size()*10; i++) {
            this.positions.add(0L);
        }
        this.coordinates = new HashMap<>();
    }

    public Map<Coordinate, Color> getCoordinates() {
        return this.coordinates;
    }

    public Coordinate getCurrentCoordinate() {
        return this.currentCoordinate;
    }

    public void printImage() {
        IntSummaryStatistics xSummary = coordinates.keySet().stream().mapToInt(Coordinate::getX).summaryStatistics();
        IntSummaryStatistics ySummary = coordinates.keySet().stream().mapToInt(Coordinate::getY).summaryStatistics();
        for(int y = ySummary.getMin(); y <= ySummary.getMax(); y++) {
            for(int x = xSummary.getMin(); x <= xSummary.getMax(); x++) {
                Color color = coordinates.getOrDefault(new Coordinate(x, y), Color.BLACK);
                System.out.print(color == Color.WHITE ? '#' : ' ');
            }
            System.out.println();
        }
    }


    public Long compute() {
        long currentOperation = 0;

        while (currentOperation != 99) {
            LinkedList<Integer> opcode = retrieveOpcode(positions.get(currentPosition).intValue());
            currentOperation = concat(opcode.pollFirst(), opcode.pollFirst());
            if (currentOperation == 1) {
                sum(opcode);
            } else if (currentOperation == 2) {
                multiply(opcode);
            } else if (currentOperation == 3) {
                readInput(opcode);
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
            } else if (currentOperation == 9) {
                updateRelativeBase(opcode);
            } else if (currentOperation != 99) {
                throw new RuntimeException("hum: " + currentOperation);
            }
        }

        return positions.get(0);
    }

    private void sum(LinkedList<Integer> opcode) {
        Long valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        Long valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
        Long sum = valueA + valueB;
        setValue(opcode, currentPosition + 3, sum, currentRelativeBase, positions);
        currentPosition += 4;
    }

    private void multiply(LinkedList<Integer> opcode) {
        Long valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        Long valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
        Long multiplied = valueA * valueB;
        setValue(opcode, currentPosition + 3, multiplied, currentRelativeBase, positions);
        currentPosition += 4;
    }

    private void readInput(LinkedList<Integer> opcode) {
        Color color = coordinates.getOrDefault(currentCoordinate, Color.BLACK);
        Long value = color.getLongValue();
        setValue(opcode, currentPosition + 1, value, currentRelativeBase, positions);
        currentPosition += 2;
    }

    private void processOutput(LinkedList<Integer> opcode) {
        Long value = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        outputs.add(value);

        if (outputs.size() % 2 == 0) {
            Color color = Color.fromLongValue(outputs.get(outputs.size() - 2));
            coordinates.put(currentCoordinate, color);
            System.out.print("-- Color: " + color);

            int direction = Math.toIntExact(outputs.get(outputs.size() - 1));
            currentDirection = currentDirection.calculateDirection(direction);
            currentCoordinate = currentDirection.calculateNextCoordinate(currentCoordinate);
            System.out.print("-- Next: " + currentCoordinate.getX() + "; " + currentCoordinate.getY());
            System.out.println();
        }

        currentPosition += 2;
    }

    private void jumpIfTrue(LinkedList<Integer> opcode) {
        Long valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        if (valueA.intValue() != 0) {
            Long value = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
            currentPosition = value.intValue();
        } else {
            currentPosition += 3;
        }
    }

    private void jumpIfFalse(LinkedList<Integer> opcode) {
        Long valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        if (valueA.intValue() == 0) {
            Long value = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
            currentPosition = value.intValue();
        } else {
            currentPosition += 3;
        }
    }

    private void lessThan(LinkedList<Integer> opcode) {
        Long valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        Long valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
        Long result = (valueA.compareTo(valueB) < 0) ? 1L : 0L;
        setValue(opcode, currentPosition + 3, result, currentRelativeBase, positions);
        currentPosition += 4;
    }

    private void equals(LinkedList<Integer> opcode) {
        Long valueA = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        Long valueB = retrieveValue(opcode, currentPosition + 2, currentRelativeBase, positions);
        Long result = (valueA.equals(valueB)) ? 1L : 0L;
        setValue(opcode, currentPosition + 3, result, currentRelativeBase, positions);
        currentPosition += 4;
    }

    private void updateRelativeBase(LinkedList<Integer> opcode) {
        Long value = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        currentRelativeBase = currentRelativeBase + value;
        currentPosition += 2;
    }

    private LinkedList<Integer> retrieveOpcode(Integer current) {
        LinkedList<Integer> opcode = new LinkedList<>();
        while (current > 0) {
            opcode.add(current % 10);
            current = current / 10;
        }
        return opcode;
    }

    private Integer concat(Integer int2, Integer int1) {
        //coming in in the wrong order (last one first), so turning both integers here
        String string1 = toInteger(int1);
        String string2 = toInteger(int2);
        String result = string1 + string2;
        return Integer.parseInt(result);
    }

    private String toInteger(Integer number) {
        return (number == null) ? "0" : Integer.toString(number);
    }

    private Long retrieveValue(LinkedList<Integer> opcode, int positionToRetrieveFrom, Long relativeBase, List<Long> positions) {
        Integer mode = opcode.pollFirst();
        if (mode == null || mode == 0) { //position mode
            int position = positions.get(positionToRetrieveFrom).intValue();
            return positions.get(position);
        } else if (mode == 1) { //immediate mode
            return positions.get(positionToRetrieveFrom);
        } else { //relative mode (mode = 2)
            int position = positions.get(positionToRetrieveFrom).intValue() + relativeBase.intValue();
            return positions.get(position);
        }
    }

    private void setValue(LinkedList<Integer> opcode, int positionToSetValueTo, Long value, Long relativeBase, List<Long> positions) {
        Integer mode = opcode.pollFirst();
        if (mode == null || mode == 0) { //position mode
            int position = positions.get(positionToSetValueTo).intValue();
            positions.set(position, value);
        } else if (mode == 1) { //immediate mode
            positions.set(positionToSetValueTo, value);
        } else { //relative mode (mode = 2)
            int position = positions.get(positionToSetValueTo).intValue() + relativeBase.intValue();
            positions.set(position, value);
        }
    }

}
