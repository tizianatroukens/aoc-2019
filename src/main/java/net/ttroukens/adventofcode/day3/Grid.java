package net.ttroukens.adventofcode.day3;

import common.Projector;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid implements Projector<Integer> {
    private static final Coordinate CENTRAL_PORT = new Coordinate(1, 1);

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        List<Wire> wires = Arrays.stream(firstLine.split(";"))
                                 .map(Grid::createWire)
                                 .collect(Collectors.toList());
        return computeClosestCrossingPoint(wires.get(0), wires.get(1));
    }

    private static Wire createWire(String input) {
        List<Coordinate> coordinates = new ArrayList<>();

        Coordinate current = CENTRAL_PORT;
        for (String instruction : input.split(",")) {
            char direction = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            for (int i = 1; i <= value; i++) {
                int x = (direction == 'R') ? current.getX() + i : ((direction == 'L') ? current.getX() - i : current.getX());
                int y = (direction == 'U') ? current.getY() + i : ((direction == 'D') ? current.getY() - i : current.getY());
                Coordinate next = new Coordinate(x, y);
                coordinates.add(next);
            }

            current = coordinates.get(coordinates.size() - 1);
        }

        return new Wire(coordinates);
    }

    private Integer computeClosestCrossingPoint(Wire one, Wire two) {
        Collection<Coordinate> intersections = CollectionUtils.intersection(one.getCoordinates(), two.getCoordinates());
        return intersections.stream()
                            .map(Grid::calculateManhattanDistanceWithCentralPort)
                            .reduce(Integer::min)
                            .orElse(0);
    }

    private static Integer calculateManhattanDistanceWithCentralPort(Coordinate coordinate) {
        return (Math.abs(coordinate.getX()) - Math.abs(CENTRAL_PORT.getX())) +
                (Math.abs(coordinate.getY()) - Math.abs(CENTRAL_PORT.getY()));
    }

}
