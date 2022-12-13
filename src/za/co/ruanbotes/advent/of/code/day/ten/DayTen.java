package za.co.ruanbotes.advent.of.code.day.ten;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DayTen {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/ten/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/ten/input.txt";

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
        List<Integer> signalStrengths = calculateSignalStrengths(lines);
        System.out.println("Signal Strength: " + calculateSumOfSignalStrengths(signalStrengths));
    }

    private Integer calculateSumOfSignalStrengths(List<Integer> signalStrengths) {
        Integer strength = 0;

        for (Integer signal: signalStrengths) {
            strength += signal;
        }

        return strength;
    }

    private List<Integer> calculateSignalStrengths(List<String> lines) {
        List<Integer> signalStrengths = new ArrayList<>();

        int cycle = 0;
        int x = 1;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("noop")) {
                cycle++;
                if (isSignalCalcCycle(cycle)) {
                    signalStrengths.add(x * cycle);
                }
            }
            else {
                String[] parts = lines.get(i).split(" ");
                if (parts[0].equals("addx")) {
                    Integer value = Integer.parseInt(parts[1]);
                    cycle++;
                    if (isSignalCalcCycle(cycle)) {
                        signalStrengths.add(x * cycle);
                    }
                    cycle++;
                    if (isSignalCalcCycle(cycle)) {
                        signalStrengths.add(x * cycle);
                    }
                    x += value;
                }
            }
        }

        return signalStrengths;
    }

    private boolean isSignalCalcCycle(int cycle) {
        return cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        List<String> screen = drawScreen(lines);
        for (int i = 0; i < screen.size(); i++) {
            System.out.println(screen.get(i));
        }
    }

    private List<String> drawScreen(List<String> lines) {
        List<String> screen = new ArrayList<>();
        screen.add("");
        screen.add("");
        screen.add("");
        screen.add("");
        screen.add("");
        screen.add("");
        int cycle = 0;
        int x = 1;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("noop")) {
                cycle++;
                boolean inSprint = inSprite(screen.get(getRowNumber(cycle)).length(), x-1);
                screen.set(getRowNumber(cycle), screen.get(getRowNumber(cycle)).concat(inSprint ? "#" : "."));
            }
            else {
                String[] parts = lines.get(i).split(" ");
                if (parts[0].equals("addx")) {
                    Integer value = Integer.parseInt(parts[1]);
                    cycle++;
                    screen.set(getRowNumber(cycle), screen.get(getRowNumber(cycle)).concat(inSprite(screen.get(getRowNumber(cycle)).length(), x-1) ? "#" : "."));
                    cycle++;
                    screen.set(getRowNumber(cycle), screen.get(getRowNumber(cycle)).concat(inSprite(screen.get(getRowNumber(cycle)).length(), x-1) ? "#" : "."));
                    x += value;
                }
            }
        }

        return screen;
    }

    private boolean inSprite(int position, int sprite) {
        if (position >= sprite && position < sprite + 3) {
            return true;
        }

        return false;
    }

    private int getRowNumber(int cycle) {
        if (cycle <= 40) {
            return 0;
        } else if (cycle <= 80) {
            return 1;
        } else if (cycle <= 120) {
            return 2;
        } else if (cycle <= 160) {
            return 3;
        } else if (cycle <= 200) {
            return 4;
        } else if (cycle <= 240) {
            return 5;
        } else {
            return -1;
        }
    }
}
