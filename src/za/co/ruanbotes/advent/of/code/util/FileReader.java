package za.co.ruanbotes.advent.of.code.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private FileReader() {

    }
    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return lines;
    }

    public static List<List<Integer>> readFileToNumberGrid(String path) {
        List<List<Integer>> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split("");
                List<Integer> digits = new ArrayList<>();
                for (String digit: parts) {
                    digits.add(Integer.parseInt(digit));
                }
                lines.add(digits);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return lines;
    }

    public static List<List<Character>> readFileToCharacterGrid(String path) {
        List<List<Character>> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split("");
                List<Character> chars = new ArrayList<>();
                for (String digit: parts) {
                    chars.add(digit.charAt(0));
                }
                lines.add(chars);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return lines;
    }
}
