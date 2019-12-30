package net.ttroukens.adventofcode.day11;

import java.util.Objects;

public enum Color {
    BLACK(0L), WHITE(1L);

    private Long longValue;

    private Color(Long longValue) {
        this.longValue = longValue;
    }

    public Long getLongValue() {
        return this.longValue;
    }

    public static Color fromLongValue(Long longValue) {
        for (Color color : Color.values()) {
            if (Objects.equals(color.longValue, longValue)) {
                return color;
            }
        }

        throw new RuntimeException("Wrong output... Trying to construct color from value " + longValue);
    }
}
