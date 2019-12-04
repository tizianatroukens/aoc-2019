package net.ttroukens.adventofcode.day1;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleFuelTest {
    private Projector projection = new DoubleFuel();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("14"))).isEqualTo(2);
        assertThat(projection.project(Stream.of("1969"))).isEqualTo(966);
        assertThat(projection.project(Stream.of("100756"))).isEqualTo(50346);
    }
}