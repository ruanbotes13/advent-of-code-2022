package za.co.ruanbotes.advent.of.code.day.one;

public class Elf {
    private Integer calories;

    public Elf() {
        this.calories = 0;
    }

    public void addCalories(Integer calories) {
        this.calories += calories;
    }

    public Integer getCalories() {
        return this.calories;
    }
}
