package net.ttroukens.adventofcode.day6;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OrbitDistanceTest {
    private Projector projection = new OrbitDistance();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN"))).isEqualTo(4);
    }
}
