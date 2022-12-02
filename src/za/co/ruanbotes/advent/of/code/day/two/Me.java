package za.co.ruanbotes.advent.of.code.day.two;

public enum Me {
    ROCK("X", 1),
    PAPER("Y", 2),
    SCISSORS("Z", 3);

    private String text;
    private int score;

    Me(String text, int score) {
        this.text = text;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static Me getFromString(String value) {
        for (Me b : Me.values()) {
            if (b.text.equalsIgnoreCase(value)) {
                return b;
            }
        }
        return null;
    }
}
