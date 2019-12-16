package net.ttroukens.adventofcode.day7;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AmplifierTest {
    private Projector projection = new Amplifier();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"))).isEqualTo(43210);
        assertThat(projection.project(Stream.of("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"))).isEqualTo(54321);
        assertThat(projection.project(Stream.of("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"))).isEqualTo(65210);
    }
}
