package net.ttroukens.adventofcode.day14;

import common.Projector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class NanoFactory implements Projector<Integer> {
    private Map<Chemical, Long> producedByQuantity = new HashMap<>();
    private Map<String, List<Chemical>> chemicalsWithNecessaryResources = new HashMap<>();
    private Map<String, Long> waste = new HashMap<>();

    private int mode;

    public NanoFactory(int mode) {
        this.mode = mode;
    }

    @Override
    public Integer project(Stream<String> lines) {
        lines.forEach(line -> {
            String[] chemicals = line.split("=>");

            String outputChemicalName = parseChemical(chemicals[1]);
            long outputChemicalProducedQuantity = parseLong(chemicals[1]);
            Chemical outputChemical = new Chemical(outputChemicalName, outputChemicalProducedQuantity);
            producedByQuantity.put(outputChemical, outputChemicalProducedQuantity);

            List<Chemical> inputChemicals = new ArrayList<>();
            for (String input : chemicals[0].split(",")) {
                String inputChemicalName = parseChemical(input);
                long inputChemicalNeededQuantity = parseLong(input);
                inputChemicals.add(new Chemical(inputChemicalName, inputChemicalNeededQuantity));
            }
            chemicalsWithNecessaryResources.put(outputChemicalName, inputChemicals);
        });

        if (mode != 0) { //part 2
            return maxAmountOfFuelWith1TrillionOre();
        }

        return requirementsForFuel(1).intValue();
    }

    private Long parseLong(String value) {
        return Long.parseLong(value.replaceAll("[^\\d]", ""));
    }

    private String parseChemical(String value) {
        return value.replaceAll("[\\d\\s]", "");
    }

    private Long requirementsForFuel(int required) {
        return chemicalsWithNecessaryResources.get("FUEL")
                .stream()
                .map(inputChemical -> requirementsForChemical(inputChemical, required))
                .reduce(Long::sum)
                .orElse(0L);
    }

    private Long requirementsForChemical(Chemical chemical, int required) {
        if (chemical.getName().equals("ORE")) {
            return required * chemical.getProduced();
        } else {
            long inWaste = waste.getOrDefault(chemical.getName(), 0L);
            long neededQuantity = required * chemical.getProduced() - inWaste;
            long producedByQuantity = this.producedByQuantity.get(chemical);
            int toActuallyProduce = (int) Math.ceil((double) neededQuantity / (double) producedByQuantity);
            long toPutIntoWaste = (producedByQuantity * toActuallyProduce) - neededQuantity;
            waste.compute(chemical.getName(), (key, value) -> toPutIntoWaste == 0 ? null : toPutIntoWaste);
            return chemicalsWithNecessaryResources.get(chemical.getName())
                    .stream()
                    .map(inputChemical -> requirementsForChemical(inputChemical, toActuallyProduce))
                    .reduce(Long::sum)
                    .orElse(0L);
        }
    }

    private int maxAmountOfFuelWith1TrillionOre() {
        double maxOreNecessary = 1E12;
        int currentFuelProduced = 1;

        while (true) {
            Long currentOreNecessary = requirementsForFuel(currentFuelProduced);
            if (currentOreNecessary < maxOreNecessary) {
                currentFuelProduced++;
            } else if (currentOreNecessary > maxOreNecessary) {
                currentFuelProduced--;
                break;
            } else {
                break;
            }
        }

        return currentFuelProduced;
    }
}
