package za.co.ruanbotes.advent.of.code.day.two;

public enum Result {
    LOSE("X", 0),
    DRAW("Y", 3),
    WIN("Z", 6);

    private String text;
    private int score;
    Result(String text, int score) {
        this.text = text;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static Result getFromString(String value) {
        for (Result b : Result.values()) {
            if (b.text.equalsIgnoreCase(value)) {
                return b;
            }
        }
        return null;
    }
}
