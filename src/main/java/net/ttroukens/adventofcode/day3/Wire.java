package net.ttroukens.adventofcode.day3;

import java.util.List;

public class Wire {
    private List<Coordinate> coordinates;

    public Wire(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Coordinate> getCoordinates() {
        return this.coordinates;
    }
}
