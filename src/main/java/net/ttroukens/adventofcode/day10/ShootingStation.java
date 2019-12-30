package net.ttroukens.adventofcode.day10;

import common.Projector;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

public class ShootingStation extends MonitoringStation implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        parseInput(lines.toArray(String[]::new));
        Asteroid locationOfStation = asteroids.stream()
                                              .max(comparing(this::getVisibleAsteroidsCount))
                                              .orElseThrow(() ->
                                                      new RuntimeException("No location to place the station!")
                                              );

        Map<Double, LinkedList<Asteroid>> byAngle = asteroids.stream()
                .filter(not(locationOfStation::equals))
                .sorted(comparing(locationOfStation::manhattanDistance))
                .collect(groupingBy(locationOfStation::getAngle, toCollection(LinkedList::new)));
        Double[] allAngles = byAngle.keySet()
                                    .stream()
                                    .sorted()
                                    .toArray(Double[]::new);

        int angle = 0;
        LinkedList<Asteroid> killed = new LinkedList<>();
        while (killed.size() < 200) {
            int angleToAim = angle++ % allAngles.length; //because we'll loop several times!
            Asteroid toKill = byAngle.get(allAngles[angleToAim]).poll();
            if (toKill != null) {
                killed.add(toKill);
            }
        }

        Asteroid number200 = killed.getLast();
        return number200.getX() * 100 + number200.getY();
    }
}