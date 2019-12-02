package common;

import net.persgroep.adventofcode.day1.DoubleFuel;
import net.persgroep.adventofcode.day1.Fuel;
import net.persgroep.adventofcode.day2.Gravity;
import net.persgroep.adventofcode.day2.GravityInverted;

public class Application {
    public static void main(String[] args) {
        System.out.println("Advent of code");
        System.out.println("--------------");

        Projector<Integer> projectorFuel = new Fuel();
        System.out.println(new Eventstore<>(projectorFuel).replay("input-01.txt"));

        Projector<Integer> projectorDoubleFuel = new DoubleFuel();
        System.out.println(new Eventstore<>(projectorDoubleFuel).replay("input-01.txt"));

        Projector<Integer> projectorGravity = new Gravity();
        System.out.println(new Eventstore<>(projectorGravity).replay("input-02.txt"));

        Projector<Integer> projectorGravityInverted = new GravityInverted();
        System.out.println(new Eventstore<>(projectorGravityInverted).replay("input-02.txt"));
    }

}
