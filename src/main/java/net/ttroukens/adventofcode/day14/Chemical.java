package net.ttroukens.adventofcode.day14;

import java.util.Objects;

public class Chemical {
    private String name;
    private Long produced;

    public Chemical(String name, Long produced) {
        this.name = name;
        this.produced = produced;
    }

    public String getName() {
        return this.name;
    }

    public Long getProduced() {
        return this.produced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chemical chemical = (Chemical) o;
        return Objects.equals(name, chemical.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
