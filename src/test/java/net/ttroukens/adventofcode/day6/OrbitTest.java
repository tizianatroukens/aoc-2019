package net.ttroukens.adventofcode.day6;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OrbitTest {
    private Projector projection = new Orbit();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L"))).isEqualTo(42);
    }
}
