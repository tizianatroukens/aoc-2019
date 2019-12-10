package net.ttroukens.adventofcode.day5;

import common.Projector;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GravityAdvancedTest {
    private Projector projection = new GravityAdvanced();

    @Test
    public void testBasic() {
        assertThat(projection.project(Stream.of("1002,4,3,4,33"))).isEqualTo(1002);
    }

    @Test
    public void positionMode_equality() {
        System.out.println("Should print 1");
        String input = "8";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,9,8,9,10,9,4,9,99,-1,8"))).isEqualTo(3);
        System.out.println();

        System.out.println("Should print 0");
        input = "1";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,9,8,9,10,9,4,9,99,-1,8"))).isEqualTo(3);
        System.out.println();
    }

    @Test
    public void positionMode_lessThan() {
        System.out.println("Should print 1");
        String input = "7";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,9,7,9,10,9,4,9,99,-1,8"))).isEqualTo(3);
        System.out.println();

        System.out.println("Should print 0");
        input = "9";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,9,7,9,10,9,4,9,99,-1,8"))).isEqualTo(3);
        System.out.println();
    }

    @Test
    public void positionMode_jumpTest() {
        System.out.println("Should print 1");
        String input = "7";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9"))).isEqualTo(3);
        System.out.println();

        System.out.println("Should print 0");
        input = "0";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9"))).isEqualTo(3);
        System.out.println();
    }

    @Test
    public void immediateMode_equality() {
        System.out.println("Should print 1");
        String input = "8";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,3,1108,-1,8,3,4,3,99"))).isEqualTo(3);
        System.out.println();

        System.out.println("Should print 0");
        input = "1";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,3,1108,-1,8,3,4,3,99"))).isEqualTo(3);
        System.out.println();
    }

    @Test
    public void immediateMode_lessThan() {
        System.out.println("Should print 1");
        String input = "7";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,3,1107,-1,8,3,4,3,99"))).isEqualTo(3);
        System.out.println();

        System.out.println("Should print 0");
        input = "9";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,3,1107,-1,8,3,4,3,99"))).isEqualTo(3);
        System.out.println();
    }

    @Test
    public void immediateMode_jumpTest() {
        System.out.println("Should print 1");
        String input = "7";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,3,1105,-1,9,1101,0,0,12,4,12,99,1"))).isEqualTo(3);
        System.out.println();

        System.out.println("Should print 0");
        input = "0";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(projection.project(Stream.of("3,3,1105,-1,9,1101,0,0,12,4,12,99,1"))).isEqualTo(3);
        System.out.println();
    }
}
