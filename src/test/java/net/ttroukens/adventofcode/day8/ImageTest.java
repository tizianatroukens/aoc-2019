package net.ttroukens.adventofcode.day8;

import common.Projector;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {
    private Projector projection = new Image();

    @Before
    public void setup() {
        Image.setWidth(3);
        Image.setHeight(2);
    }

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("123456789012"))).isEqualTo(1);
    }
}
