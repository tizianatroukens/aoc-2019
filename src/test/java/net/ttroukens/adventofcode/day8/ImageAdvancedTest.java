package net.ttroukens.adventofcode.day8;

import common.Projector;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageAdvancedTest {
    private Projector projection = new ImageAdvanced();

    @Before
    public void setup() {
        ImageAdvanced.setWidth(2);
        ImageAdvanced.setHeight(2);
    }

    @Test
    public void test() {
        assertThat(projection.project(Stream.of("0222112222120000"))).isEqualTo("0110");
    }
}
