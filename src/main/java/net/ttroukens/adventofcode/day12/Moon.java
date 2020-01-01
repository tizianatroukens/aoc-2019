package net.ttroukens.adventofcode.day12;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class Moon {
    private Integer[] position;
    private Integer[] velocity = new Integer[]{0,0,0};

    Moon(Moon moon) {
        this.position = Arrays.copyOf(moon.position, moon.position.length);
        this.velocity = Arrays.copyOf(moon.velocity, moon.velocity.length);
    }

    Moon(Integer[] positions) {
        this.position = positions;
    }

    static UnaryOperator<Moon> withAxis(Integer axis) {
        return moon -> new Moon(new Integer[]{moon.position[axis]});
    }

    void applyGravity(Moon other) {
        for (int v = 0; v < position.length; v++) {
            int currentValue = position[v];
            int otherValue = other.position[v];

            if (currentValue != otherValue) {
                velocity[v] = (currentValue < otherValue) ? velocity[v]+1 : velocity[v]-1;
                other.velocity[v] = (currentValue < otherValue) ? other.velocity[v]-1 : other.velocity[v]+1;
            }
        }
    }

    void applyVelocity() {
        for (int p = 0; p < position.length; p++) {
            position[p] = position[p] + velocity[p];
        }
    }

    Integer[] getPosition() {
        return this.position;
    }

    int calculateEnergy() {
        return calculateEnergy(position) * calculateEnergy(velocity);
    }

    private int calculateEnergy(Integer[] sources) {
        return Arrays.stream(sources).map(Math::abs).reduce(Integer::sum).orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return Arrays.equals(position, moon.position);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(position);
    }
}
