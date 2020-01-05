package net.ttroukens.adventofcode.day13;

import java.util.*;

public class IntCodeComputer {
    private int currentPosition = 0;
    private Long currentRelativeBase = 0L;

    private List<Long> positions;
    private LinkedList<Long> outputs = new LinkedList<>();

    private Map<Coordinate, TileType> coordinates;
    private Coordinate ball;
    private Coordinate paddle;
    private Long score = -1L;

    public IntCodeComputer(List<Long> positions) {
        this.positions = new ArrayList<>(positions);
        for (int i = positions.size(); i < positions.size()*10; i++) {
            this.positions.add(0L);
        }
        this.coordinates = new HashMap<>();
    }

    public List<Long> getPositions() {
        return this.positions;
    }

    public Map<Coordinate, TileType> getCoordinates() {
        return this.coordinates;
    }

    public Long getScore() {
        return this.score;
    }

    public void printGame() {
        IntSummaryStatistics xSummary = coordinates.keySet().stream().mapToInt(Coordinate::getX).summaryStatistics();
        IntSummaryStatistics ySummary = coordinates.keySet().stream().mapToInt(Coordinate::getY).summaryStatistics();
        for(int y = ySummary.getMin(); y <= ySummary.getMax(); y++) {
            for(int x = xSummary.getMin(); x <= xSummary.getMax(); x++) {
                TileType tileType = coordinates.getOrDefault(new Coordinate(x, y), TileType.EMPTY);
                System.out.print(tileType.getPrintValue());
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
        Long value = (long)Long.compare(ball.getX(), paddle.getX());
        setValue(opcode, currentPosition + 1, value, currentRelativeBase, positions);
        currentPosition += 2;
    }

    private void processOutput(LinkedList<Integer> opcode) {
        Long value = retrieveValue(opcode, currentPosition + 1, currentRelativeBase, positions);
        outputs.add(value);

        if (outputs.size() % 3 == 0) {
            Long xValue = outputs.get(outputs.size() - 3);
            Long yValue = outputs.get(outputs.size() - 2);
            Long outputValue = outputs.get(outputs.size() - 1);
            if (xValue == -1L && yValue == 0L) {
                score = outputValue;
            } else {
                Coordinate coordinate = new Coordinate(xValue.intValue(), yValue.intValue());
                TileType tileType = TileType.fromLongValue(outputValue);
                coordinates.put(coordinate, tileType);
                if (tileType == TileType.BALL) ball = coordinate;
                if (tileType == TileType.PADDLE) paddle = coordinate;
            }
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
