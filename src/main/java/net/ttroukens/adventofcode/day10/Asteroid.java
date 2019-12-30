package net.ttroukens.adventofcode.day10;

import java.util.Objects;

public class Asteroid {
    private int x;
    private int y;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Double getAngle(Asteroid other) {
        double angle = 90 - Math.toDegrees(Math.atan2(y - other.getY(), other.getX() - x));
        return angle < 0 ? (angle + 360) : angle;
    }

    public int manhattanDistance(Asteroid other) {
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asteroid asteroid = (Asteroid) o;
        return x == asteroid.x &&
                y == asteroid.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}