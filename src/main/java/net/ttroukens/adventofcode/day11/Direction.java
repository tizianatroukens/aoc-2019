package net.ttroukens.adventofcode.day11;

public enum Direction {
    UP, LEFT, RIGHT, DOWN;

    public Direction calculateDirection(int direction) {
        switch (this) {
            case UP:    return direction == 0 ? LEFT : RIGHT;
            case DOWN:  return direction == 0 ? RIGHT : LEFT;
            case LEFT:  return direction == 0 ? DOWN : UP;
            default:    return direction == 0 ? UP : DOWN;
        }
    }

    public Coordinate calculateNextCoordinate(Coordinate coordinate) {
        switch (this) {
            case UP:    return new Coordinate(coordinate.getX(), coordinate.getY() - 1);
            case DOWN:  return new Coordinate(coordinate.getX(), coordinate.getY() + 1);
            case LEFT:  return new Coordinate(coordinate.getX() - 1, coordinate.getY());
            default:    return new Coordinate(coordinate.getX() + 1, coordinate.getY());
        }
    }
}
