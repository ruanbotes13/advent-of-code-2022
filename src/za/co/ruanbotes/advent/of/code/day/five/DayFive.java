package za.co.ruanbotes.advent.of.code.day.five;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DayFive {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/five/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/five/input.txt";

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
        int splitter = lines.indexOf("");
        List<Stack<String>> chart = populateChart(lines.subList(0, splitter));
        List<Instruction> instructions = getInstructions(lines.subList(splitter+1, lines.size()));
        chart = playInstructions(chart, instructions);
        System.out.println(getMessage(chart));

    }

    public List<Stack<String>> populateChart(List<String> lines) {
        List<Stack<String>> chart = new ArrayList<>();
        String[] words = lines.get(lines.size() - 1).split("(?<=\\G.{" + 4 + "})");

        for (int i = 0; i < words.length; i++) {
            chart.add(new Stack<>());
        }

        for (int i = lines.size() - 2; i >= 0; i--) {
            String[] crates = lines.get(i).split("(?<=\\G.{" + 4 + "})");
            for (int j = 0; j < crates.length; j++) {
                if (crates[j].contains("[")) {
                    chart.get(j).push(crates[j].substring(0, 3));
                }
            }
        }

        return chart;
    }

    public List<Instruction> getInstructions(List<String> lines) {
        List<Instruction> instructions = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(" ");
            instructions.add(new Instruction(Integer.parseInt(parts[1]), Integer.parseInt(parts[3]), Integer.parseInt(parts[5])));
        }

        return instructions;
    }

    public List<Stack<String>> playInstructions(List<Stack<String>> chart, List<Instruction> instructions) {
        List<Stack<String>> chartToPlay = chart;

        for (int i = 0; i < instructions.size(); i++) {
            for (int j = 0; j < instructions.get(i).getNumber(); j++) {
                chartToPlay.get(instructions.get(i).getDestination()).push(chartToPlay.get(instructions.get(i).getSource()).pop());
            }
        }

        return chartToPlay;
    }

    public String getMessage(List<Stack<String>> chart) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < chart.size(); i++) {
            String value = chart.get(i).pop().replace("[", "").replace("]", "");
            message.append(value);
        }

        return message.toString();
    }

    public void printChart(List<Stack<String>> chart) {
        for (int i = 0; i < chart.size(); i++) {
            for (int j = 0; j < chart.get(i).size(); j++) {
                System.out.print("\"" + chart.get(i).get(j) + "\"");
            }
            System.out.print("\n");
        }
    }

    public List<Stack<String>> playInstructionsAdvanced(List<Stack<String>> chart, List<Instruction> instructions) {
        List<Stack<String>> chartToPlay = chart;

        for (int i = 0; i < instructions.size(); i++) {
            Stack<String> tempStack = new Stack<>();
            for (int j = 0; j < instructions.get(i).getNumber(); j++) {
                tempStack.push(chartToPlay.get(instructions.get(i).getSource()).pop());
            }

            while (!tempStack.empty()) {
                chartToPlay.get(instructions.get(i).getDestination()).push(tempStack.pop());
            }
        }

        return chartToPlay;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        int splitter = lines.indexOf("");
        List<Stack<String>> chart = populateChart(lines.subList(0, splitter));
        List<Instruction> instructions = getInstructions(lines.subList(splitter+1, lines.size()));
        chart = playInstructionsAdvanced(chart, instructions);
        System.out.println(getMessage(chart));
    }
}
