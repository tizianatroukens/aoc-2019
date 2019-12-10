package net.ttroukens.adventofcode.day6;

import common.Projector;

import java.util.List;
import java.util.stream.Stream;

public class OrbitDistance implements Projector<Integer> {

    @Override
    public Integer project(Stream<String> lines) {
        lines.map(v -> v.split("\\)"))
             .forEach(Orbit::parseTheUniverse);
        return calculateDistanceToSanta();
    }

    private static int calculateDistanceToSanta() {
        Planet you = Orbit.PLANETS.get("YOU");
        Planet santa = Orbit.PLANETS.get("SAN");
        Planet commonPlanet = findCommonPlanet(you, santa);
        return (you.calculateDistanceToOtherPlanet(commonPlanet) - 1) +
                (santa.calculateDistanceToOtherPlanet(commonPlanet) - 1);
    }

    private static Planet findCommonPlanet(Planet you, Planet santa) {
        List<Planet> planetsAboveYou = you.getAllPlanetsUpToRoot();
        for (Planet planetAboveSanta : santa.getAllPlanetsUpToRoot()) {
            if (planetsAboveYou.contains(planetAboveSanta)) {
                return planetAboveSanta;
            }
        }

        return null;
    }
}
