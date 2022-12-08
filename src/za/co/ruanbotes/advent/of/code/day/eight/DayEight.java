package za.co.ruanbotes.advent.of.code.day.eight;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.List;

public class DayEight {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/eight/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/eight/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(INPUT);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(INPUT);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<List<Integer>> grid = FileReader.readFileToNumberGrid(path);
        System.out.println("Number visible: " + getVisibleTreeCount(grid));
    }

    private int getVisibleTreeCount(List<List<Integer>> grid) {
        int count = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (i == 0 || j == 0 || i == grid.size() - 1 || j == grid.get(i).size() - 1) {
                    count++;
                } else {
                    if (visibleUp(grid, i, j) || visibleDown(grid, i, j) || visibleLeft(grid, i, j) || visibleRight(grid, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean visibleUp(List<List<Integer>> grid, int y, int x) {
        for (int row = y; row > 0; row--) {
            if (grid.get(y).get(x) <= grid.get(row - 1).get(x)) {
                return false;
            }
        }

        return true;
    }

    private boolean visibleDown(List<List<Integer>> grid, int y, int x) {
        for (int row = y; row < grid.get(y).size() - 1; row++) {
            if (grid.get(y).get(x) <= grid.get(row + 1).get(x)) {
                return false;
            }
        }

        return true;
    }

    private boolean visibleLeft(List<List<Integer>> grid, int y, int x) {
        for (int column = x; column > 0; column--) {
            if (grid.get(y).get(x) <= grid.get(y).get(column - 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean visibleRight(List<List<Integer>> grid, int y, int x) {
        for (int column = x; column < grid.get(y).size() - 1; column++) {
            if (grid.get(y).get(x) <= grid.get(y).get(column + 1)) {
                return false;
            }
        }

        return true;
    }

    public void partTwo(String path) {
        List<List<Integer>> grid = FileReader.readFileToNumberGrid(path);
        System.out.println("Largest Scenic Distance: " + getBestScenicDistance(grid));
    }

    private int getBestScenicDistance(List<List<Integer>> grid) {
        int scenicDistance = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (!isEdgeTree(grid, i, j) && isVisibleTree(grid, i, j)) {
                    int distance = scenicScoreUp(grid, i, j) * scenicScoreDown(grid, i, j) * scenicScoreLeft(grid, i, j) * scenicScoreRight(grid, i, j);
                    if (distance > scenicDistance) {
                        scenicDistance = distance;
                    }
                }
            }
        }

        return scenicDistance;
    }

    private boolean isEdgeTree(List<List<Integer>> grid, int i, int j) {
        return !(i > 0 && j > 0 && i < grid.size() - 1 && j < grid.get(i).size() - 1);
    }

    private boolean isVisibleTree(List<List<Integer>> grid, int i, int j) {
        return visibleUp(grid, i, j) || visibleDown(grid, i, j) || visibleLeft(grid, i, j) || visibleRight(grid, i, j);
    }

    private int scenicScoreUp(List<List<Integer>> grid, int y, int x) {
        int scenicDistance = 0;
        for (int row = y; row > 0; row--) {
            if (grid.get(y).get(x) <= grid.get(row - 1).get(x)) {
                return ++scenicDistance;
            } else {
                scenicDistance++;
            }
        }

        return scenicDistance;
    }

    private int scenicScoreDown(List<List<Integer>> grid, int y, int x) {
        int scenicDistance = 0;
        for (int row = y; row < grid.get(y).size() - 1; row++) {
            if (grid.get(y).get(x) <= grid.get(row + 1).get(x)) {
                return ++scenicDistance;
            } else {
                scenicDistance++;
            }
        }

        return scenicDistance;
    }

    private int scenicScoreLeft(List<List<Integer>> grid, int y, int x) {
        int scenicDistance = 0;
        for (int column = x; column > 0; column--) {
            if (grid.get(y).get(x) <= grid.get(y).get(column - 1)) {
                return ++scenicDistance;
            } else {
                scenicDistance++;
            }
        }

        return scenicDistance;
    }

    private int scenicScoreRight(List<List<Integer>> grid, int y, int x) {
        int scenicDistance = 0;
        for (int column = x; column < grid.get(y).size() - 1; column++) {
            if (grid.get(y).get(x) <= grid.get(y).get(column + 1)) {
                return ++scenicDistance;
            } else {
                scenicDistance++;
            }
        }

        return scenicDistance;
    }
}
