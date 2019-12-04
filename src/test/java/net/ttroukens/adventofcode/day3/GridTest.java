package net.ttroukens.adventofcode.day3;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
    private Projector projection = new Grid();

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("R8,U5,L5,D3;U7,R6,D4,L4"))).isEqualTo(6);
        assertThat(projection.project(Stream.of("R75,D30,R83,U83,L12,D49,R71,U7,L72;U62,R66,U55,R34,D71,R55,D58,R83"))).isEqualTo(159);
        assertThat(projection.project(Stream.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51;U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"))).isEqualTo(135);
    }
}
