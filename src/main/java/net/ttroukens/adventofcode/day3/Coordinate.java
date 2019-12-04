package net.ttroukens.adventofcode.day3;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;
    private int nrOfSteps;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.nrOfSteps = 0;
    }

    public Coordinate(int x, int y, int nrOfSteps) {
        this.x = x;
        this.y = y;
        this.nrOfSteps = nrOfSteps;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getNrOfSteps() {
        return this.nrOfSteps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
