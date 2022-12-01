package za.co.ruanbotes.advent.of.code.day.one;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DayOne {

    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/one/example1.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/one/input1.txt";
    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(INPUT);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(INPUT);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        List<Elf> elves = populateElfList(lines);

        System.out.println("Max calories: " + getMaxCalories(elves));
    }

    private List<Elf> populateElfList(List<String> lines) {
        List<Elf> elves = new ArrayList<>();

        Elf elf = new Elf();

        for (String line : lines) {
            if (line.equals("")) {
                elves.add(elf);
                elf = new Elf();
            } else {
                elf.addCalories(Integer.parseInt(line));
            }
        }

        return elves;
    }

    private Integer getMaxCalories(List<Elf> elves) {
        Integer max = 0;

        for (Elf elf1 : elves) {
            if (elf1.getCalories() > max) {
                max = elf1.getCalories();
            }
        }

        return max;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        List<Elf> elves = populateElfList(lines);

        System.out.println("Top 3 total:  " + getTopByLimit(elves, 3));
    }

    public Integer getTopByLimit(List<Elf> elves, int limit) {
        elves = sort(elves);

        Integer sum = 9;

        for (int i = 0; i < limit; i++) {
            sum += elves.get(i).getCalories();
        }

        return sum;
    }

    public List<Elf> sort(List<Elf> list) {

        list.sort((o1, o2)
            -> o2.getCalories().compareTo(
            o1.getCalories()));

        return list;
    }


}
