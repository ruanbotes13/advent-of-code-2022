package za.co.ruanbotes.advent.of.code.day.eleven;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    List<Integer> items = new ArrayList<>();
    Character operation;
    Integer operator;
    Integer testCondition;
    Integer testTrue;
    Integer testFalse;
    Long inspectCounter = 0l;

    public Monkey() {

    }

    public void relief() {
        for (int i = 0; i < this.items.size(); i++) {
            this.items.set(i, Math.floorDiv(this.items.get(i), 3));
        }
    }

    public void inspectItems() {
        this.inspectCounter += this.items.size();
        for (int i = 0; i < this.items.size(); i++) {

            if (this.operation.equals('+')) {
                this.items.set(i, this.items.get(i) + (this.operator == null ? this.items.get(i) : this.operator));
            } else if (this.operation.equals('-')) {
                this.items.set(i, this.items.get(i) - (this.operator == null ? this.items.get(i) : this.operator));
            } else if (this.operation.equals('*')) {
                this.items.set(i, this.items.get(i) * (this.operator == null ? this.items.get(i) : this.operator));
            }
        }
    }

    public void addItem(Integer item) {
        this.items.add(item);
    }

    public void addOperation(Character operation) {
        this.operation = operation;
    }

    public void addOperator(Integer operator) {
        this.operator = operator;
    }

    public void setTestCondition(Integer testCondition) {
        this.testCondition = testCondition;
    }

    public void setTestTrue(Integer testTrue) {
        this.testTrue = testTrue;
    }

    public void setTestFalse(Integer testFalse) {
        this.testFalse = testFalse;
    }
}
