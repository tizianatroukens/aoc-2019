package net.persgroep.adventofcode.day2;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GravityTest {
    private Projector projection = new Gravity();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("1,9,10,3,2,3,11,0,99,30,40,50"))).isEqualTo(3500);
        assertThat(projection.project(Stream.of("1,0,0,0,99"))).isEqualTo(2);
        assertThat(projection.project(Stream.of("2,3,0,3,99"))).isEqualTo(2);
        assertThat(projection.project(Stream.of("2,4,4,5,99,0"))).isEqualTo(2);
        assertThat(projection.project(Stream.of("1,1,1,4,99,5,6,0,99"))).isEqualTo(30);
    }
}
