package za.co.ruanbotes.advent.of.code.day.two;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DayTwo {

    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/two/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/two/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(EXAMPLE);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(INPUT);
        System.out.println("==================");
    }

    private void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        List<Opponent> opponent = new ArrayList<>();
        List<Me> me = new ArrayList<>();

        lines.forEach(line -> {
            opponent.add(Opponent.getFromString(line.split(" ")[0]));
            me.add(Me.getFromString(line.split(" ")[1]));
        });

        int score = getScore(opponent, me);

        System.out.println("Score: " + score);
    }

    private int getScore(List<Opponent> opponent, List<Me> me) {
        int score = 0;

        for(int i = 0; i < opponent.size(); i++) {
            if (Opponent.ROCK.equals(opponent.get(i))) {
                if (Me.ROCK.equals(me.get(i))) {
                    score += (3 + me.get(i).getScore());
                } else if (Me.PAPER.equals(me.get(i))) {
                    score += (6 + me.get(i).getScore());
                } else {
                    score += me.get(i).getScore();
                }
            } else if (Opponent.PAPER.equals(opponent.get(i))) {
                if (Me.ROCK.equals(me.get(i))) {
                    score += (me.get(i).getScore());
                } else if (Me.PAPER.equals(me.get(i))) {
                    score += (3 + me.get(i).getScore());
                } else {
                    score += (6 + me.get(i).getScore());
                }
            } else if (Opponent.SCISSORS.equals(opponent.get(i))) {
                if (Me.ROCK.equals(me.get(i))) {
                    score += (6 + me.get(i).getScore());
                } else if (Me.PAPER.equals(me.get(i))) {
                    score += (me.get(i).getScore());
                } else {
                    score += (3 + me.get(i).getScore());
                }
            }
        }

        return score;
    }

    private void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        List<Opponent> opponent = new ArrayList<>();
        List<Result> result = new ArrayList<>();

        lines.forEach(line -> {
            opponent.add(Opponent.getFromString(line.split(" ")[0]));
            result.add(Result.getFromString(line.split(" ")[1]));
        });

        int score = getResultScore(opponent, result);

        System.out.println("Score: " + score);
    }

    private int getResultScore(List<Opponent> opponent, List<Result> result) {
        int score = 0;

        for(int i = 0; i < opponent.size(); i++) {
            if (Opponent.ROCK.equals(opponent.get(i))) {
                if (Result.LOSE.equals(result.get(i))) {
                    score += (Me.SCISSORS.getScore() + result.get(i).getScore());
                } else if (Result.DRAW.equals(result.get(i))) {
                    score += (Me.ROCK.getScore() + result.get(i).getScore());
                } else {
                    score += (Me.PAPER.getScore() + result.get(i).getScore());
                }
            } else if (Opponent.PAPER.equals(opponent.get(i))) {
                if (Result.LOSE.equals(result.get(i))) {
                    score += (Me.ROCK.getScore() + result.get(i).getScore());
                } else if (Result.DRAW.equals(result.get(i))) {
                    score += (Me.PAPER.getScore() + result.get(i).getScore());
                } else {
                    score += (Me.SCISSORS.getScore() + result.get(i).getScore());
                }
            } else if (Opponent.SCISSORS.equals(opponent.get(i))) {
                if (Result.LOSE.equals(result.get(i))) {
                    score += (Me.PAPER.getScore() + result.get(i).getScore());
                } else if (Result.DRAW.equals(result.get(i))) {
                    score += (Me.SCISSORS.getScore() + result.get(i).getScore());
                } else {
                    score += (Me.ROCK.getScore() + result.get(i).getScore());
                }
            }
        }

        return score;
    }
}
