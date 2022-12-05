package za.co.ruanbotes.advent.of.code.day.three;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DayThree {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/three/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/three/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(EXAMPLE);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(EXAMPLE);
        System.out.println("==================");
    }

    private void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        int prioritySum = calculatePriorities(lines);
        System.out.println("Sum: " + prioritySum);
    }

    private int calculatePriorities(List<String> lines) {
        int prioritySum = 0;

        for (String line : lines) {
            String compartmentOne = line.substring(0, (line.length()/2));
            String compartmentTwo = line.substring((line.length()/2));
            int priority = calculateItemPriority(compartmentOne, compartmentTwo);
            prioritySum += priority;
        }

        return prioritySum;
    }

    private int calculateItemPriority(String c1, String c2) {
        int priority = 0;
        Set<Character> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(findUniqueCommonValues(c1, c2));

        for (char i : linkedHashSet) {
            priority += getItemValue(i);
        }

        return priority;
    }

    private int getItemValue(char item) {
        if (Character.isUpperCase(item)) {
            return (item - 38);
        } else {
            return (item - 96);
        }
    }

    private Set<Character> findUniqueCommonValues(String c1, String c2) {
        Set<Character> linkedHashSet = new LinkedHashSet<>();

        for (int i = 0; i < c1.length(); i++) {
            char item = c1.charAt(i);
            int place = c2.indexOf(item);
            if (place > -1) {
                linkedHashSet.add(item);
            }
        }

        return linkedHashSet;
    }

    private void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        int prioritySum = calculatePrioritiesAdvanced(lines);
        System.out.println("Sum: " + prioritySum);
    }

    private int calculatePrioritiesAdvanced(List<String> lines) {
        int prioritySum = 0;

        for (int i = 0; i < lines.size(); i = i + 3) {
            String compartment1 = lines.get(i);
            String compartment2 = lines.get(i + 1);
            String compartment3 = lines.get(i + 2);
            int priority = calculateItemPriorityAdvanced(compartment1, compartment2, compartment3);
            prioritySum += priority;
        }

        return prioritySum;
    }

    private int calculateItemPriorityAdvanced(String c1, String c2, String c3) {
        int priority = 0;
        Set<Character> linkedHashSet = new LinkedHashSet<>();
        if (c1.length() >= c2.length() && c1.length() >= c3.length()) {
            linkedHashSet.addAll(findUniqueCommonValuesAdvanced(c1, c2, c3));
        } else if (c2.length() >= c1.length() && c2.length() >= c3.length()) {
            linkedHashSet.addAll(findUniqueCommonValuesAdvanced(c2, c1, c3));
        } else {
            linkedHashSet.addAll(findUniqueCommonValuesAdvanced(c3, c1, c2));
        }

        for (char i : linkedHashSet) {
            priority += getItemValue(i);
        }

        return priority;
    }

    private Set<Character> findUniqueCommonValuesAdvanced(String c1, String c2, String c3) {
        Set<Character> linkedHashSet = new LinkedHashSet<>();

        for (int i = 0; i < c1.length(); i++) {
            char item = c1.charAt(i);
            int place1 = c2.indexOf(item);
            int place2 = c3.indexOf(item);

            if (place1 > -1 && place2 > -1) {
                linkedHashSet.add(item);
            }
        }

        return linkedHashSet;
    }
}
