package net.ttroukens.adventofcode.day10;

import common.Projector;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MonitoringStationTest {
    private Projector projection = new MonitoringStation();

    @Test
    public void test() {
        String input =  ".#..#\n" +
                        ".....\n" +
                        "#####\n" +
                        "....#\n" +
                        "...##";
        assertThat(projection.project(Stream.of(input.split("\n")))).isEqualTo(8);
    }

    @Test
    public void test2() {
        String input =  "......#.#.\n" +
                        "#..#.#....\n" +
                        "..#######.\n" +
                        ".#.#.###..\n" +
                        ".#..#.....\n" +
                        "..#....#.#\n" +
                        "#..#....#.\n" +
                        ".##.#..###\n" +
                        "##...#..#.\n" +
                        ".#....####";
        assertThat(projection.project(Stream.of(input.split("\n")))).isEqualTo(33);
    }

    @Test
    public void test3() {
        String input =  "#.#...#.#.\n" +
                        ".###....#.\n" +
                        ".#....#...\n" +
                        "##.#.#.#.#\n" +
                        "....#.#.#.\n" +
                        ".##..###.#\n" +
                        "..#...##..\n" +
                        "..##....##\n" +
                        "......#...\n" +
                        ".####.###.";
        assertThat(projection.project(Stream.of(input.split("\n")))).isEqualTo(35);
    }

    @Test
    public void test4() {
        String input =  ".#..#..###\n" +
                        "####.###.#\n" +
                        "....###.#.\n" +
                        "..###.##.#\n" +
                        "##.##.#.#.\n" +
                        "....###..#\n" +
                        "..#.#..#.#\n" +
                        "#..#.#.###\n" +
                        ".##...##.#\n" +
                        ".....#.#..";
        assertThat(projection.project(Stream.of(input.split("\n")))).isEqualTo(41);
    }

    @Test
    public void test5() {
        String input =  ".#..##.###...#######\n" +
                        "##.############..##.\n" +
                        ".#.######.########.#\n" +
                        ".###.#######.####.#.\n" +
                        "#####.##.#.##.###.##\n" +
                        "..#####..#.#########\n" +
                        "####################\n" +
                        "#.####....###.#.#.##\n" +
                        "##.#################\n" +
                        "#####.##.###..####..\n" +
                        "..######..##.#######\n" +
                        "####.##.####...##..#\n" +
                        ".#####..#.######.###\n" +
                        "##...#.##########...\n" +
                        "#.##########.#######\n" +
                        ".####.#.###.###.#.##\n" +
                        "....##.##.###..#####\n" +
                        ".#.#.###########.###\n" +
                        "#.#.#.#####.####.###\n" +
                        "###.##.####.##.#..##";
        assertThat(projection.project(Stream.of(input.split("\n")))).isEqualTo(210);
    }
}
