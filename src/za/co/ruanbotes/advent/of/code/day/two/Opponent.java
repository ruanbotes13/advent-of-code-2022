package za.co.ruanbotes.advent.of.code.day.two;

public enum Opponent {
    ROCK("A"),
    PAPER("B"),
    SCISSORS("C");

    private String text;

    Opponent(String text) {
        this.text = text;
    }

    public static Opponent getFromString(String value) {
        for (Opponent b : Opponent.values()) {
            if (b.text.equalsIgnoreCase(value)) {
                return b;
            }
        }
        return null;
    }
}
