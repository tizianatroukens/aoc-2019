package common;

import net.ttroukens.adventofcode.day1.DoubleFuel;
import net.ttroukens.adventofcode.day1.Fuel;
import net.ttroukens.adventofcode.day2.Gravity;
import net.ttroukens.adventofcode.day2.GravityInverted;
import net.ttroukens.adventofcode.day3.Grid;
import net.ttroukens.adventofcode.day3.TimeSensitiveGrid;
import net.ttroukens.adventofcode.day4.AdvancedPassword;
import net.ttroukens.adventofcode.day4.Password;

public class Application {
    public static void main(String[] args) {
        System.out.println("Advent of code");
        System.out.println("--------------");

        Projector<Integer> projectorFuel = new Fuel();
        System.out.println("Day 1: " + new Eventstore<>(projectorFuel).replay("input-01.txt"));

        Projector<Integer> projectorDoubleFuel = new DoubleFuel();
        System.out.println("Day 1 Advanced: " + new Eventstore<>(projectorDoubleFuel).replay("input-01.txt"));

        Projector<Integer> projectorGravity = new Gravity();
        System.out.println("Day 2: " + new Eventstore<>(projectorGravity).replay("input-02.txt"));

        Projector<Integer> projectorGravityInverted = new GravityInverted();
        System.out.println("Day 2 Advanced: " + new Eventstore<>(projectorGravityInverted).replay("input-02.txt"));

        Projector<Integer> projectorGrid = new Grid();
        System.out.println("Day 3: " + new Eventstore<>(projectorGrid).replay("input-03.txt"));

        Projector<Integer> projectorTimeSensitiveGrid = new TimeSensitiveGrid();
        System.out.println("Day 3 Advanced: " + new Eventstore<>(projectorTimeSensitiveGrid).replay("input-03.txt"));

        Projector<Integer> projectorPassword = new Password();
        System.out.println("Day 4: " + new Eventstore<>(projectorPassword).replay("input-04.txt"));

        Projector<Integer> projectorAdvancedPassword = new AdvancedPassword();
        System.out.println("Day 4 Advanced: " + new Eventstore<>(projectorAdvancedPassword).replay("input-04.txt"));
    }

}
