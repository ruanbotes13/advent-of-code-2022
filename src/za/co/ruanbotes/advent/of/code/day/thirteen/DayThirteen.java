package za.co.ruanbotes.advent.of.code.day.thirteen;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DayThirteen {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/thirteen/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/thirteen/input.txt";

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
        List<Packet> packets = parsePackets(lines);
    }

    private List<Packet> parsePackets(List<String> lines) {
        List<Packet> packets = new ArrayList<>();

        for (int i = 0; i < lines.size() - 1; i = i + 3) {
            Packet left = new Packet(getStackFromLine(lines.get(i)));
            Packet right = new Packet(getStackFromLine(lines.get(i+1)));

            left.parsePacketContent();
            right.parsePacketContent();

            packets.add(left);
            packets.add(right);
        }

        return packets;
    }

    private Stack<Character> getStackFromLine(String line) {
        Stack<Character> stack = new Stack<>();

        for (int i = line.length() - 2; i > 0; i--) {
            stack.push(line.charAt(i));
        }

        return stack;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
    }
}
