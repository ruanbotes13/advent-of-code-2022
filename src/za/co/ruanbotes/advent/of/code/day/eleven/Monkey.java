package za.co.ruanbotes.advent.of.code.day.eleven;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    List<Long> items = new ArrayList<>();
    Character operation;
    Long operator = null;
    Integer testCondition;
    Integer testTrue;
    Integer testFalse;
    long inspectCounter = 0;

    public Monkey() {

    }

    public void relief() {
        for (int i = 0; i < this.items.size(); i++) {
            this.items.set(i, Math.floorDiv(this.items.get(i), 3));
        }
    }

    public void relief(int relief) {
        for (int i = 0; i < this.items.size(); i++) {
            this.items.set(i, this.items.get(i) % relief);
        }
    }

    public void inspectItems() {
        for (int i = 0; i < this.items.size(); i++) {
            this.inspectCounter++;
            if (this.operation.equals('+')) {
                Long operator = getOperator(i);
                this.items.set(i, this.items.get(i) + operator);
            } else if (this.operation.equals('-')) {
                Long operator = getOperator(i);
                this.items.set(i, this.items.get(i) - operator);
            } else if (this.operation.equals('*')) {
                Long operator = getOperator(i);
                this.items.set(i, this.items.get(i) * operator);
            }
        }
    }

    private Long getOperator(int item) {
        if (this.operator == null) {
            return this.items.get(item);
        } else {
            return this.operator;
        }
    }

    public void addItem(Long item) {
        this.items.add(item);
    }

    public void addOperation(Character operation) {
        this.operation = operation;
    }

    public void addOperator(Long operator) {
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
