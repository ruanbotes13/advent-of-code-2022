package za.co.ruanbotes.advent.of.code.day.five;

public class Instruction {
    private int number;
    private int source;
    private int destination;

    Instruction(int number, int source, int destination) {
        this.number = number;
        this.source = source - 1;
        this.destination = destination - 1;
    }

    public void print() {
        System.out.println(number + " from " + source + " to " + destination);
    }

    public int getNumber() {
        return this.number;
    }

    public int getSource() {
        return this.source;
    }

    public int getDestination() {
        return this.destination;
    }
}
