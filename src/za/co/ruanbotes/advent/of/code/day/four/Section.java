package za.co.ruanbotes.advent.of.code.day.four;

public class Section {
    private int min;
    private int max;

    Section(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean containsSection(Section section) {
        return this.min <= section.min && this.max >= section.max;
    }

    public boolean containsOverlap(Section section) {
        return (section.min >= this.min && section.min <= this.max) || (section.max >= this.min && section.max <= this.max);
    }
}
