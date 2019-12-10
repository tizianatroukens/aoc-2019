package net.ttroukens.adventofcode.day6;

import common.Projector;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Orbit implements Projector<Integer> {
    static Map<String, Planet> PLANETS = new HashMap<>();

    @Override
    public Integer project(Stream<String> lines) {
        lines.map(v -> v.split("\\)"))
             .forEach(Orbit::parseTheUniverse);
        return calculateTotalNumberOfOrbits();
    }

    static void parseTheUniverse(String[] inputLine) {
        String orbiting = inputLine[0];
        String orbiter = inputLine[1];

        Planet root = PLANETS.computeIfAbsent(orbiting, v -> new Planet());
        if (PLANETS.putIfAbsent(orbiter, new Planet(root)) != null) {
            PLANETS.get(orbiter).setRoot(root);
        }
    }

    private static int calculateTotalNumberOfOrbits() {
        return PLANETS.values()
                      .stream()
                      .map(Planet::calculateDistanceToRoot)
                      .reduce(0, Integer::sum);
    }
}
