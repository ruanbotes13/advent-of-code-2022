package za.co.ruanbotes.advent.of.code.day.seven;

public class File {
    private String name;
    private int size;
    private Directory parent;

    File(String name, int size, Directory parent) {
        this.name = name;
        this.size = size;
        this.parent = parent;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public Directory getParent() {
        return this.parent;
    }
}
