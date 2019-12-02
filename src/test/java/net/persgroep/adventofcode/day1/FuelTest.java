package net.persgroep.adventofcode.day1;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FuelTest {
    private Projector projection = new Fuel();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("12"))).isEqualTo(2);
        assertThat(projection.project(Stream.of("14"))).isEqualTo(2);
        assertThat(projection.project(Stream.of("1969"))).isEqualTo(654);
        assertThat(projection.project(Stream.of("100756"))).isEqualTo(33583);
    }
}
