package net.ttroukens.adventofcode.day13;

import java.util.Objects;

public enum TileType {
    EMPTY(true, 0L, " "),
    WALL(false, 1L, "|"),
    BLOCK(true, 2L, "#"),
    PADDLE(false, 3L, "-"),
    BALL(true, 4L, "O");

    private boolean destructible;
    private Long longValue;
    private String printValue;

    public boolean isDestructible() {
        return this.destructible;
    }

    public Long getLongValue() {
        return this.longValue;
    }

    public String getPrintValue() {
        return this.printValue;
    }

    private TileType(boolean destructible, Long longValue, String printValue) {
        this.destructible = destructible;
        this.longValue = longValue;
        this.printValue = printValue;
    }

    public static TileType fromLongValue(Long longValue) {
        for (TileType tileType : TileType.values()) {
            if (Objects.equals(tileType.longValue, longValue)) {
                return tileType;
            }
        }

        throw new RuntimeException("Wrong output... Trying to construct tile from value " + longValue);
    }
}
