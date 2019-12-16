package common;

import net.ttroukens.adventofcode.day1.DoubleFuel;
import net.ttroukens.adventofcode.day1.Fuel;
import net.ttroukens.adventofcode.day2.Gravity;
import net.ttroukens.adventofcode.day2.GravityInverted;
import net.ttroukens.adventofcode.day3.Grid;
import net.ttroukens.adventofcode.day3.TimeSensitiveGrid;
import net.ttroukens.adventofcode.day4.AdvancedPassword;
import net.ttroukens.adventofcode.day4.Password;
import net.ttroukens.adventofcode.day5.GravityAdvanced;
import net.ttroukens.adventofcode.day6.Orbit;
import net.ttroukens.adventofcode.day6.OrbitDistance;
import net.ttroukens.adventofcode.day7.Amplifier;
import net.ttroukens.adventofcode.day7.AmplifierLoop;

public class Application {
    public static void main(String[] args) {
        System.out.println("Advent of code");
        System.out.println("--------------");

        run(false, new Fuel(), "Day 1: ", "input-01.txt");
        run(false, new DoubleFuel(), "Day 1 Advanced: ", "input-01.txt");
        run(false, new Gravity(), "Day 2: ", "input-02.txt");
        run(false, new GravityInverted(), "Day 2 Advanced: ", "input-02.txt");
        run(false, new Grid(), "Day 3: ", "input-03.txt");
        run(false, new TimeSensitiveGrid(), "Day 3 Advanced: ", "input-03.txt");
        run(false, new Password(), "Day 4: ", "input-04.txt");
        run(false, new AdvancedPassword(), "Day 4 Advanced: ", "input-04.txt");
        run(false, new GravityAdvanced(), "Day 5: ", "input-05.txt");
        run(false, new Orbit(), "Day 6: ", "input-06.txt");
        run(false, new OrbitDistance(), "Day 6 Advanced: ", "input-06.txt");
        run(false, new Amplifier(), "Day 7: ", "input-07.txt");
        run(true, new AmplifierLoop(), "Day 7 Advanced: ", "input-07.txt");
    }

    private static void run(boolean run, Projector<Integer> projector, String title, String inputFile) {
        if (run) {
            System.out.println(title + new Eventstore<>(projector).replay(inputFile));
        }
    }

}
