package za.co.ruanbotes.advent.of.code.day.four;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DayFour {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/four/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/four/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(EXAMPLE);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(INPUT);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        List<List<Section>> sectionList = populateSections(lines);
        System.out.println("Containing sections: " + countContainingSections(sectionList));
    }

    public List<List<Section>> populateSections(List<String> lines) {
        List<List<Section>> sectionsList = new ArrayList<>();

        for (String line : lines) {
            String[] pairs = line.split(",");
            List<Section> sections = new ArrayList<>();

            sections.add(new Section(Integer.parseInt(pairs[0].split("-")[0]), Integer.parseInt(pairs[0].split("-")[1])));
            sections.add(new Section(Integer.parseInt(pairs[1].split("-")[0]), Integer.parseInt(pairs[1].split("-")[1])));

            sectionsList.add(sections);
        }

        return sectionsList;
    }

    public int countContainingSections(List<List<Section>> sectionList) {
        int counter = 0;

        for (List<Section> sections : sectionList) {
            if (sections.get(0).containsSection(sections.get(1)) || sections.get(1).containsSection(sections.get(0))) {
                counter++;
            }
        }

        return counter;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        List<List<Section>> sectionList = populateSections(lines);
        System.out.println("Overlapping sections: " + countOverlappingSections(sectionList));
    }

    public int countOverlappingSections(List<List<Section>> sectionList) {
        int counter = 0;

        for (List<Section> sections : sectionList) {
            if (sections.get(0).containsOverlap(sections.get(1)) || sections.get(1).containsOverlap(sections.get(0))) {
                counter++;
            }
        }

        return counter;
    }
}
