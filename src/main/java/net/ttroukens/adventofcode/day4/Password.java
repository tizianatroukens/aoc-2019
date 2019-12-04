package net.ttroukens.adventofcode.day4;

import common.Projector;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Password implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        List<Integer> input = lines.map(line -> line.split("-"))
                                   .map(Arrays::stream)
                                   .flatMap(Function.identity())
                                   .map(Integer::parseInt)
                                   .collect(Collectors.toList());

        return (int) IntStream.range(input.get(0), input.get(1))
                              .filter(password -> isFittingPassword(String.valueOf(password)))
                              .count();
    }

    static boolean isFittingPassword(String password) {
        boolean hasDouble = false;

        int current = -1;
        for (int index = 0; index < password.length(); index++) {
            int next = Character.getNumericValue(password.charAt(index));
            if (!hasDouble && next == current) {
                hasDouble = true;
            }
            if (next < current) {
                return false;
            }
            current = next;
        }

        return hasDouble;
    }
}
