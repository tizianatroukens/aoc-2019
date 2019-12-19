package net.ttroukens.adventofcode.day9;

import common.Projector;
import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RevisitingGravityTest {
    private Projector projection = new RevisitingGravity();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"))).isEqualTo(new BigInteger("109"));
        assertThat(projection.project(Stream.of("1102,34915192,34915192,7,4,7,99,0"))).isEqualTo(new BigInteger("1102"));
        assertThat(projection.project(Stream.of("104,1125899906842624,99"))).isEqualTo(new BigInteger("104"));
    }
}
