package za.co.ruanbotes.advent.of.code.day.thirteen;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Packet {
    Integer number;
    List<Packet> packets;
    Stack<Character> characterStack;

    Packet() {
        this.packets = new ArrayList<>();
    }

    Packet(Integer number) {
        this.packets = new ArrayList<>();
        this.number = number;
    }

    Packet(Stack<Character> characterStack) {
        this.packets = new ArrayList<>();
        this.characterStack = characterStack;
    }

    public Stack<Character> parsePacketContent() {
        while(!this.characterStack.isEmpty()) {
            if (this.characterStack.peek().equals('[')) {
                this.characterStack.pop();
                Packet child = new Packet(characterStack);
                this.characterStack = child.parsePacketContent();
                this.packets.add(child);
            } else if (this.characterStack.peek().equals(']')) {
                this.characterStack.pop();
                return this.characterStack;
            } else {
                while(!this.characterStack.isEmpty() && !this.characterStack.peek().equals('[') && !this.characterStack.peek().equals(']')) {
                    String number = "";

                    while (!this.characterStack.isEmpty() && !this.characterStack.peek().equals(',') && !this.characterStack.peek().equals('[') && !this.characterStack.peek().equals(']')) {
                        number += this.characterStack.pop();
                    }

                    if (number.length() > 0) {
                        this.packets.add(new Packet(Integer.parseInt(number)));
                    }

                    if (!this.characterStack.isEmpty() && this.characterStack.peek().equals(',')) {
                        this.characterStack.pop();
                    }
                }
            }
        }

        return this.characterStack;
    }
}
