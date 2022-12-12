package za.co.ruanbotes.advent.of.code.day.nine;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class DayNine {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/nine/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/nine/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(EXAMPLE);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(EXAMPLE);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        playMoves(lines);
    }

    public List<GridBlock> playMoves(List<String> lines) {
        List<GridBlock> visited = new ArrayList<>();

        int currentHeadRow = 0;
        int currentHeadColumn = 0;

        int currentTailRow = 0;
        int currentTailColumn = 0;

        for (int i = 0; i < lines.size(); i++) {
            String direction = lines.get(i).split(" ")[0];
            int distance = Integer.parseInt(lines.get(i).split(" ")[1]);

            for (int j = 0; j < distance; j++) {
                if (direction.equals("R")) {
                    currentHeadColumn++;

                    if (currentTailRow == currentHeadRow) { // SAME ROW
                        if(currentHeadColumn - currentTailColumn >= 2) {
                            currentTailColumn++;
                        }
                    }
                } else if (direction.equals("L")) {
                    currentHeadColumn--;

                    if (currentTailRow == currentHeadRow) { // SAME ROW
                        if(currentHeadColumn - currentTailColumn >= 2) {
                            currentTailColumn--;
                        }
                    }
                } else if (direction.equals("D")) {
                    currentHeadRow++;

                    if (currentTailColumn == currentHeadColumn) { // SAME ROW
                        if(currentHeadRow - currentTailRow >= 2) {
                            currentTailRow++;
                        }
                    }
                } else if (direction.equals("U")) {
                    currentHeadRow--;

                    if (currentTailColumn == currentHeadColumn) { // SAME ROW
                        if(currentHeadRow - currentTailRow >= 2) {
                            currentTailRow--;
                        }
                    }
                }
            }
        }

        return visited;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
    }
}
