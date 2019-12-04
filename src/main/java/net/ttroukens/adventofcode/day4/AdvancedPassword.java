package net.ttroukens.adventofcode.day4;

import common.Projector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdvancedPassword implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        List<Integer> input = lines.map(line -> line.split("-"))
                                   .map(Arrays::stream)
                                   .flatMap(Function.identity())
                                   .map(Integer::parseInt)
                                   .collect(Collectors.toList());

        return (int) IntStream.range(input.get(0), input.get(1))
                              .filter(password -> isAdvancedFittingPassword(String.valueOf(password)))
                              .count();
    }

    static boolean isAdvancedFittingPassword(String password) {
        int current = Character.getNumericValue(password.charAt(0)), count = 1;
        List<Integer> counts = new ArrayList<>();

        for (int index = 1; index < password.length(); index++) {
            int next = Character.getNumericValue(password.charAt(index));

            if (next == current) {
                count += 1;
            } else {
                counts.add(count);
                count = 1;
            }

            if (next < current) {
                return false;
            }

            current = next;
        }

        counts.add(count);
        return counts.contains(2);
    }

}
