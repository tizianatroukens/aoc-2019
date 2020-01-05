package net.ttroukens.adventofcode.day13;

import common.Projector;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArcadeCabinet implements Projector<Integer> {
    private int mode;

    public ArcadeCabinet(int mode) {
        this.mode = mode;
    }

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        IntCodeComputer intCodeComputer = new IntCodeComputer(
                Arrays.stream(firstLine.split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toList())
        );

        if (mode != 0) { //part 2
            intCodeComputer.getPositions().set(0, 2L);
        }

        intCodeComputer.compute();
        intCodeComputer.printGame();

        if (mode != 0) {
            return intCodeComputer.getScore().intValue();
        }

        return (int) intCodeComputer.getCoordinates()
                                    .values()
                                    .stream()
                                    .filter(type -> type.equals(TileType.BLOCK))
                                    .count();
    }
}
