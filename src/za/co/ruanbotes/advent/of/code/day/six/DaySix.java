package za.co.ruanbotes.advent.of.code.day.six;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.List;

public class DaySix {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/six/example.txt";
    private static final String EXAMPLE2 = "resources/za/co/ruanbotes/advent/of/code/day/six/example2.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/six/input.txt";

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
        System.out.println("Position: " + findMarkerPosition(lines.get(0), 4));
    }

    private int findMarkerPosition(String line, int markerLength) {
        char[] markers = new char[markerLength];
        for (int i = 0; i < line.length(); i++) {
            if (i < markerLength) {
                markers[i] = line.charAt(i);
            }
            else {
                if (isMarkersUnique(markers)) {
                    return i;
                }
                else {
                    for (int j = 1; j < markers.length; j++) {
                        markers[j-1] = markers[j];
                    }

                    markers[markers.length-1] = line.charAt(i);
                }
            }
        }

        return 0;
    }

    private boolean isMarkersUnique(char[] markers) {
        boolean unique = true;

        for (int i = 0; i < markers.length; i++) {
            if (countOccurances(markers[i], markers) > 1) {
                unique = false;
                break;
            }
        }

        return unique;
    }

    private int countOccurances(char c, char[] array) {
        int count = 0;
        for (char x : array) {
            if (x == c) {
                count++;
            }
        }
        return count;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        System.out.println("Position: " + findMarkerPosition(lines.get(0), 14));
    }
}
