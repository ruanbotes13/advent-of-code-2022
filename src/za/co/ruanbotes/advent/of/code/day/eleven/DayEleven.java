package za.co.ruanbotes.advent.of.code.day.eleven;

import za.co.ruanbotes.advent.of.code.day.seven.Directory;
import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DayEleven {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/eleven/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/eleven/input.txt";

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
        List<Monkey> monkeys = populateMonkeys(lines);
        monkeys = playRounds(monkeys, 20);
        monkeys = sort(monkeys);
        System.out.println("Monkey Business Levek: " + (monkeys.get(0).inspectCounter * monkeys.get(1).inspectCounter));
    }

    public List<Monkey> populateMonkeys(List<String> lines) {
        List<Monkey> monkeys = new ArrayList<>();

        for (int i = 0; i <= lines.size() - 6;) {
            Monkey monkey = new Monkey();

            // Add items
            String itemString = lines.get(i+1).substring(18);
            String[] parts = itemString.split(", ");
            for (int j = 0; j < parts.length; j++) {
                monkey.addItem(Integer.parseInt(parts[j]));
            }

            // Add Operation
            String operationString = lines.get(i+2).substring(23);
            monkey.addOperation(operationString.charAt(0));

            // Add Operator
            String operator = operationString.substring(2);

            if (!operator.equals("old")) {
                monkey.addOperator(Integer.parseInt(operator));
            }

            // Add Test
            String testCondition = lines.get(i+3).substring(21);
            monkey.setTestCondition(Integer.parseInt(testCondition));
            String testTrue = lines.get(i+4).substring(29);
            String testFalse = lines.get(i+5).substring(30);
            monkey.setTestTrue(Integer.parseInt(testTrue));
            monkey.setTestFalse(Integer.parseInt(testFalse));

            monkeys.add(monkey);
            i = i + 7;
        }

        return monkeys;
    }

    List<Monkey> playRounds(List<Monkey> monkeys, int rounds) {
        for (int round = 0; round < rounds; round++) {
            for (int monkey = 0; monkey < monkeys.size(); monkey++) {
                if (monkeys.get(monkey).items.size() > 0) {
                    monkeys.get(monkey).inspectItems();
                    monkeys.get(monkey).relief();
                    for (int monkeyItem = 0; monkeyItem < monkeys.get(monkey).items.size(); monkeyItem++) {
                        if (monkeys.get(monkey).items.get(monkeyItem) % monkeys.get(monkey).testCondition == 0) {
                            monkeys.get(monkeys.get(monkey).testTrue).addItem(monkeys.get(monkey).items.get(monkeyItem).intValue());
                        } else {
                            monkeys.get(monkeys.get(monkey).testFalse).addItem(monkeys.get(monkey).items.get(monkeyItem).intValue());
                        }
                    }
                    monkeys.get(monkey).items = new ArrayList<>();
                }
            }
        }

        return monkeys;
    }

    public List<Monkey> sort(List<Monkey> list) {

        list.sort((o1, o2) -> {
                if (o2.inspectCounter < o1.inspectCounter) {
                    return -1;
                } else if (o1.inspectCounter < o2.inspectCounter) {
                    return 1;
                }
                return 0;
            }
        );

        return list;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        List<Monkey> monkeys = populateMonkeys(lines);
        monkeys = playRoundsWithoutRelief(monkeys, 20);
        monkeys = sort(monkeys);
        System.out.println("Monkey Business Levek: " + (monkeys.get(0).inspectCounter * monkeys.get(1).inspectCounter));
    }

    List<Monkey> playRoundsWithoutRelief(List<Monkey> monkeys, int rounds) {
        for (int round = 0; round < rounds; round++) {
            for (int monkey = 0; monkey < monkeys.size(); monkey++) {
                if (monkeys.get(monkey).items.size() > 0) {
                    monkeys.get(monkey).inspectItems();
                    if (round + 1 % 20 == 0) {
                        monkeys.get(monkey).relief();
                    }
                    for (int monkeyItem = 0; monkeyItem < monkeys.get(monkey).items.size(); monkeyItem++) {
                        if (monkeys.get(monkey).items.get(monkeyItem) % monkeys.get(monkey).testCondition == 0) {
                            monkeys.get(monkeys.get(monkey).testTrue).addItem(monkeys.get(monkey).items.get(monkeyItem).intValue());
                        } else {
                            monkeys.get(monkeys.get(monkey).testFalse).addItem(monkeys.get(monkey).items.get(monkeyItem).intValue());
                        }
                    }
                    monkeys.get(monkey).items = new ArrayList<>();
                }
            }
        }

        return monkeys;
    }
}
