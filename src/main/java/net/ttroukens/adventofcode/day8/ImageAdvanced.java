package net.ttroukens.adventofcode.day8;

import com.google.common.base.Splitter;
import common.Projector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ImageAdvanced implements Projector<String> {
    private static int WIDTH = 25;
    private static int HEIGHT = 6;

    static void setWidth(int width) {
        WIDTH = width;
    }

    static void setHeight(int height) {
        HEIGHT = height;
    }

    @Override
    public String project(Stream<String> lines) {
        String firstLine = lines.findFirst().orElseThrow(() -> new RuntimeException("no input"));
        Iterable<String> layers = Splitter.fixedLength(WIDTH * HEIGHT).split(firstLine);

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < WIDTH * HEIGHT; index++) {
            if (index % WIDTH == 0) {
                System.out.println();
            }

            for (String layer : layers) {
                int numericValue = Character.getNumericValue(layer.charAt(index));
                if (numericValue == 0) {
                    stringBuilder.append(numericValue);
                    System.out.print(" ");
                    break;
                } else if (numericValue == 1) {
                    stringBuilder.append(numericValue);
                    System.out.print("#");
                    break;
                }
            }
        }

        System.out.println();
        System.out.println();

        return stringBuilder.toString();
    }
}
