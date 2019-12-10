package net.ttroukens.adventofcode.day6;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private Planet root;

    Planet() {
        this.root = null;
    }

    Planet(Planet root) {
        this.root = root;
    }

    void setRoot(Planet root) {
        this.root = root;
    }

    Integer calculateDistanceToRoot() {
        return (root == null) ? 0 : (root.calculateDistanceToRoot() + 1);
    }

    Integer calculateDistanceToOtherPlanet(Planet that) {
        return (this.equals(that)) ? 0 : (root.calculateDistanceToOtherPlanet(that) + 1);
    }

    List<Planet> getAllPlanetsUpToRoot() {
        List<Planet> planets = new ArrayList<>();
        if (root != null) {
            planets.add(root);
            planets.addAll(root.getAllPlanetsUpToRoot());
        }
        return planets;
    }
}
