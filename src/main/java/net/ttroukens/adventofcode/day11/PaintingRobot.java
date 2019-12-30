package net.ttroukens.adventofcode.day11;

import common.Projector;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaintingRobot implements Projector<Integer> {
    private int mode;

    public PaintingRobot(int mode) {
        this.mode = mode;
    }

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        IntCodeComputer intCodeComputer = new IntCodeComputer(
                Arrays.stream(firstLine.split(","))
                                       .map(Long::parseLong)
                                       .collect(Collectors.toList())
        );

        if (mode != 0) { //part 2
            intCodeComputer.getCoordinates().put(intCodeComputer.getCurrentCoordinate(), Color.WHITE);
        }

        intCodeComputer.compute();
        intCodeComputer.printImage();
        return intCodeComputer.getCoordinates().size();
    }
}