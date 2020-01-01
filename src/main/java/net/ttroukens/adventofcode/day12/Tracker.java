package net.ttroukens.adventofcode.day12;

import common.Projector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tracker implements Projector<Long> {
    private int mode;

    public Tracker(int mode) {
        this.mode = mode;
    }

    @Override
    public Long project(Stream<String> lines) {
        List<Moon> moons = new ArrayList<>();
        lines.forEach(line -> {
            int indexOfX = line.indexOf("x=") + 2;
            int indexOfY = line.indexOf("y=") + 2;
            int indexOfZ = line.indexOf("z=") + 2;
            int x = Integer.parseInt(line.substring(indexOfX, line.indexOf(",")));
            int y = Integer.parseInt(line.substring(indexOfY, line.indexOf(",", indexOfY)));
            int z = Integer.parseInt(line.substring(indexOfZ, line.indexOf(">")));
            moons.add(new Moon(new Integer[]{x, y, z}));
        });

        return (mode == 0) ? part1(moons) : part2(moons);
    }

    private int part1(List<Moon> moons) {
        for (int i = 0; i < 1000; i++) {
            advanceStepOfTime(moons);
        }

        return moons.stream()
                    .map(Moon::calculateEnergy)
                    .reduce(Integer::sum)
                    .orElse(0);
    }

    private long part2(List<Moon> moons) {
        List<Long> nrOfStepsForOneAxis = new ArrayList<>();

        for (int axis = 0; axis < 3; axis++) {
            List<Moon> initialMoonsWithOnlyOneAxis = moons.stream()
                                                          .map(Moon::new)
                                                          .map(Moon.withAxis(axis))
                                                          .collect(Collectors.toList());

            List<Moon> moonsWithOnlyOneAxis = initialMoonsWithOnlyOneAxis.stream()
                                                                         .map(Moon::new)
                                                                         .collect(Collectors.toList());

            for (long nrOfSteps = 2L; ; nrOfSteps++) {
                advanceStepOfTime(moonsWithOnlyOneAxis);
                if (moonsWithOnlyOneAxis.equals(initialMoonsWithOnlyOneAxis)) {
                    nrOfStepsForOneAxis.add(nrOfSteps);
                    break;
                }
            }
        }

        return nrOfStepsForOneAxis.stream().reduce(Tracker::lcm).orElse(0L);
    }

    void advanceStepOfTime(List<Moon> moons) {
        for (int i = 0; i < moons.size(); i++) {
            for (int j = i+1; j < moons.size(); j++) {
                moons.get(i).applyGravity(moons.get(j));
            }
        }

        for (Moon moon : moons) {
            moon.applyVelocity();
        }
    }

    private static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            long gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }

    private static long gcd(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return number1 + number2;
        } else {
            long absNumber1 = Math.abs(number1);
            long absNumber2 = Math.abs(number2);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }
}
