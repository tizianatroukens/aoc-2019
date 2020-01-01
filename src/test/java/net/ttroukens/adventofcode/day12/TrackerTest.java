package net.ttroukens.adventofcode.day12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class TrackerTest {

    @Test
    public void test() {
        Tracker tracker = new Tracker(0);
        List<Moon> moons = new ArrayList<>(){{
            add(new Moon(new Integer[]{-1, 0, 2}));
            add(new Moon(new Integer[]{2, -10, -7}));
            add(new Moon(new Integer[]{4, -8, 8}));
            add(new Moon(new Integer[]{3, 5, -1}));
        }};

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{2, -1, 1}, new Integer[]{3, -7, -4}, new Integer[]{1, -7, 5}, new Integer[]{2, 2, 0});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{5, -3, -1}, new Integer[]{1, -2, 2}, new Integer[]{1, -4, -1}, new Integer[]{1, -4, 2});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{5, -6, -1}, new Integer[]{0, 0, 6}, new Integer[]{2, 1, -5}, new Integer[]{1, -8, 2});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{2, -8, 0}, new Integer[]{2, 1, 7}, new Integer[]{2, 3, -6}, new Integer[]{2, -9, 1});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{-1, -9, 2}, new Integer[]{4, 1, 5}, new Integer[]{2, 2, -4}, new Integer[]{3, -7, -1});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{-1, -7, 3}, new Integer[]{3, 0, 0}, new Integer[]{3, -2, 1}, new Integer[]{3, -4, -2});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{2, -2, 1}, new Integer[]{1, -4, -4}, new Integer[]{3, -7, 5}, new Integer[]{2, 0, 0});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{5, 2, -2}, new Integer[]{2, -7, -5}, new Integer[]{0, -9, 6}, new Integer[]{1, 1, 3});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{5, 3, -4}, new Integer[]{2, -9, -3}, new Integer[]{0, -8, 4}, new Integer[]{1, 1, 5});

        tracker.advanceStepOfTime(moons);
        assertPositions(moons, new Integer[]{2, 1, -3}, new Integer[]{1, -8, 0}, new Integer[]{3, -6, 1}, new Integer[]{2, 0, 4});
        assertEnergy(moons, 179);
    }

    private void assertPositions(List<Moon> moons, Integer[]... positions) {
        for (int i = 0; i < moons.size(); i++) {
            assertThat(moons.get(i).getPosition()).containsExactly(positions[i]);
        }
    }

    private void assertEnergy(List<Moon> moons, Integer totalEnergy) {
        int calculatedEnergy = moons.stream().map(Moon::calculateEnergy).reduce(Integer::sum).orElse(0);
        assertEquals(calculatedEnergy, totalEnergy, 0);
    }

    @Test
    public void testAdvanced() {
        Tracker tracker = new Tracker(1);
        assertThat(tracker.project(Stream.of("<x=-1, y=0, z=2>", "<x=2, y=-10, z=-7>", "<x=4, y=-8, z=8>", "<x=3, y=5, z=-1>"))).isEqualTo(2772);
        assertThat(tracker.project(Stream.of("<x=-8, y=-10, z=0>", "<x=5, y=5, z=10>", "<x=2, y=-7, z=3>", "<x=9, y=-8, z=-3>"))).isEqualTo(4686774924L);
    }
}