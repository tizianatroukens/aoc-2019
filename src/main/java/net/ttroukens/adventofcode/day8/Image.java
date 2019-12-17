package net.ttroukens.adventofcode.day8;

import com.google.common.base.Splitter;
import common.Projector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Image implements Projector<Integer> {
    private static int WIDTH = 25;
    private static int HEIGHT = 6;

    static void setWidth(int width) {
        WIDTH = width;
    }

    static void setHeight(int height) {
        HEIGHT = height;
    }

    @Override
    public Integer project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        Iterable<String> layers = Splitter.fixedLength(WIDTH * HEIGHT).split(firstLine);

        int minZeroes = Integer.MAX_VALUE;
        int result = -1;
        for (String layer : layers) {
            List<Integer> numbers = computeNumbers(layer);
            if (numbers.get(0) < minZeroes) {
                minZeroes = numbers.get(0);
                result = numbers.get(1) * numbers.get(2);
            }
        }

        return result;
    }

    private static List<Integer> computeNumbers(String input) {
        int numberOfZeroes = 0;
        int numberOfOnes = 0;
        int numberOfTwos = 0;

        for (int index = 0; index < input.length(); index++) {
            int numericValue = Character.getNumericValue(input.charAt(index));
            numberOfZeroes = (numericValue == 0 ? numberOfZeroes+1 : numberOfZeroes);
            numberOfOnes = (numericValue == 1 ? numberOfOnes+1 : numberOfOnes);
            numberOfTwos = (numericValue == 2 ? numberOfTwos+1 : numberOfTwos);
        }

        return Arrays.asList(numberOfZeroes, numberOfOnes, numberOfTwos);
    }
}
