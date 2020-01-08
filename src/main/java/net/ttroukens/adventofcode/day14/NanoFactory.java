package net.ttroukens.adventofcode.day14;

import common.Projector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class NanoFactory implements Projector<Integer> {
    private Map<Chemical, Integer> producedByQuantity = new HashMap<>();
    private Map<String, List<Chemical>> chemicalsWithNecessaryResources = new HashMap<>();
    private Map<String, Integer> waste = new HashMap<>();

    @Override
    public Integer project(Stream<String> lines) {
        lines.forEach(line -> {
            String[] chemicals = line.split("=>");

            String outputChemicalName = parseChemical(chemicals[1]);
            int outputChemicalProducedQuantity = parseInteger(chemicals[1]);
            Chemical outputChemical = new Chemical(outputChemicalName, outputChemicalProducedQuantity);
            producedByQuantity.put(outputChemical, outputChemicalProducedQuantity);

            List<Chemical> inputChemicals = new ArrayList<>();
            for (String input : chemicals[0].split(",")) {
                String inputChemicalName = parseChemical(input);
                int inputChemicalNeededQuantity = parseInteger(input);
                inputChemicals.add(new Chemical(inputChemicalName, inputChemicalNeededQuantity));
            }
            chemicalsWithNecessaryResources.put(outputChemicalName, inputChemicals);
        });

        return requirementsForChemical("FUEL", 1);
    }

    private int parseInteger(String value) {
        return Integer.parseInt(value.replaceAll("[^\\d]", ""));
    }

    private String parseChemical(String value) {
        return value.replaceAll("[\\d\\s]", "");
    }

    private int requirementsForChemical(String chemical, final int required) {
        return chemicalsWithNecessaryResources.get(chemical)
                .stream()
                .map(inputChemical -> requirementsForChemical(inputChemical, required))
                .reduce(Integer::sum)
                .orElse(0);
    }

    private int requirementsForChemical(Chemical chemical, int required) {
        if (chemical.getName().equals("ORE")) {
            return required * chemical.getProduced();
        } else {
            int inWaste = waste.getOrDefault(chemical.getName(), 0);
            int neededQuantity = required * chemical.getProduced() - inWaste;
            int producedByQuantity = this.producedByQuantity.get(chemical);
            int toActuallyProduce = (int) Math.ceil((double) neededQuantity / (double) producedByQuantity);
            int toPutIntoWaste = (producedByQuantity * toActuallyProduce) - neededQuantity;
            waste.compute(chemical.getName(), (key, value) -> toPutIntoWaste == 0 ? null : toPutIntoWaste);
            return chemicalsWithNecessaryResources.get(chemical.getName())
                    .stream()
                    .map(inputChemical -> requirementsForChemical(inputChemical, toActuallyProduce))
                    .reduce(Integer::sum)
                    .orElse(0);
        }
    }
}
