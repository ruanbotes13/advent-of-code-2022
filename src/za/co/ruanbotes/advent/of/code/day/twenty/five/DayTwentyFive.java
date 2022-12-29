package za.co.ruanbotes.advent.of.code.day.twenty.five;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.*;

public class DayTwentyFive {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/twenty/five/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/twenty/five/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(INPUT);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(EXAMPLE);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        long sum = calculateSum(lines);
        System.out.println("Sum: " + sum);
        System.out.println("Snafu: " + getSnafuNumber(sum));
    }

    private String getSnafuNumber(long sum) {
            String snafuNumber = "";
            long number = sum;

            while (number > 0L) {
                Map<String, Long> map = snafuDigit(number);
                for (Map.Entry<String, Long> set :
                    map.entrySet()) {
                    snafuNumber = set.getKey() + snafuNumber;
                    number = set.getValue();
                }
            }

            return snafuNumber.trim();
    }

    private Map<String, Long> snafuDigit(long number) {
        Map<String, Long> map = new HashMap<>();
        long digit = number % 5;
        long remainder = number / 5;

        if (digit == 4) {
            map.put("-", 1 + remainder);
        } else if (digit == 3) {
            map.put("=", 1 + remainder);
        } else {
            map.put(String.valueOf(digit), remainder);
        }

        return map;
    }

    public long calculateSum(List<String> lines) {
        long sum = 0;

        List<char[]> numbers = getNumberArrays(lines);
        List<Long> decimals = calculateDecimals(numbers);

        for (Long value : decimals) {
            sum += value;
        }

        return sum;
    }

    private List<Long> calculateDecimals(List<char[]> chars) {
        List<Long> decimalList = new ArrayList<>();

        for (char[] charSequence : chars) {
            long sum = 0;
            int power = charSequence.length - 1;
            for (int j = 0; j < charSequence.length; j++) {
                sum += (getNumber(charSequence[j]) * Math.pow(5, power));
                power--;
            }

            decimalList.add(sum);
        }

        return decimalList;
    }

    private int getNumber(char character) {
        if (character == '-') {
            return -1;
        } else if (character == '=') {
            return -2;
        } else {
            return Integer.parseInt(String.valueOf(character));
        }
    }

    private List<char[]> getNumberArrays(List<String> lines) {
        List<char[]> numbers = new ArrayList<char[]>();
        for (String line : lines) {
            numbers.add(line.toCharArray());
        }
        return numbers;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
    }
}
