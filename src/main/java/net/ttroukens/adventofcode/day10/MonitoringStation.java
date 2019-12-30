package net.ttroukens.adventofcode.day10;

import common.Projector;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class MonitoringStation implements Projector<Integer> {
    Set<Asteroid> asteroids = new HashSet<>();

    @Override
    public Integer project(Stream<String> lines) {
        parseInput(lines.toArray(String[]::new));
        return asteroids.stream()
                        .map(this::getVisibleAsteroidsCount)
                        .reduce(Integer::max)
                        .orElse(0);
    }

    void parseInput(String[] input) {
        asteroids.clear();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length(); x++) {
                if (input[y].charAt(x) == '#') {
                    asteroids.add(new Asteroid(x, y));
                }
            }
        }
    }

    Integer getVisibleAsteroidsCount(Asteroid asteroid) {
        Map<Double, Set<Asteroid>> byAngle = asteroids.stream()
                                                      .filter(not(asteroid::equals))
                                                      .collect(groupingBy(asteroid::getAngle, toSet()));

        return (int) asteroids.stream()
                              .filter(not(asteroid::equals))
                              .filter(secondAsteroid ->
                                      byAngle.get(asteroid.getAngle(secondAsteroid))
                                             .stream()
                                             .map(asteroid::manhattanDistance)
                                             .noneMatch(distance -> distance < asteroid.manhattanDistance(secondAsteroid))
                              ).count();
    }
}