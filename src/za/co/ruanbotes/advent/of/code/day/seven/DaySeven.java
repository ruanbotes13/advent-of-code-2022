package za.co.ruanbotes.advent.of.code.day.seven;

import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DaySeven {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/seven/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/seven/input.txt";

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(INPUT);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(INPUT);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<String> lines = FileReader.readFile(path);
        Directory root = buildDirectoryStructure(lines);
        System.out.println("Dir size: " + (root != null ? root.getSize() : 0));
        List<Directory> dirsWithMaxSize = (root != null ? root.getDirsWithMaxSize(100000) : new ArrayList<>());
        System.out.println("Dir max size: " + this.getSum(dirsWithMaxSize));
    }

    private int getSum(List<Directory> directoryList) {
        int sum = 0;

        for (int i = 0; i < directoryList.size(); i++) {
            sum += directoryList.get(i).getSize();
        }

        return sum;
    }

    private Directory buildDirectoryStructure(List<String> lines) {
        Directory root = null;
        Directory current = null;

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(" ");
            if (parts[0].equals("$")) {
                if (parts[1].equals("cd")) {
                    if (parts[2].equals("/")) {
                        if (root == null) {
                            root = new Directory(parts[2], null);
                        }
                        current = root;
                    } else if (parts[2].equals("..")) {
                        current = current != null ? current.parent : null;
                    } else {
                        if (current != null) {
                            current.addDirectory(parts[2]);
                            current = current.getDirectoryByName(parts[2]);
                        }
                    }
                }
            } else {
                if (parts[0].equals("dir") && current != null) {
                    current.addDirectory(parts[1]);
                } else if (current != null) {
                    current.addFile(parts[1], Integer.parseInt(parts[0]));
                }
            }
        }

        if (root != null) {
            root.calculateDirectorySize();
        }

        return root;
    }

    public void partTwo(String path) {
        List<String> lines = FileReader.readFile(path);
        Directory root = buildDirectoryStructure(lines);
        int totalDiskSpace = 70000000;
        int usedSpace = (root != null ? root.getSize() : 0);
        int freeSpace = totalDiskSpace - usedSpace;
        int spaceToFree = 30000000 - freeSpace;
        List<Directory> dirsWithMaxSize = sort(root.getDirsWithMinSize(spaceToFree));
        System.out.println("Dir size of dir to delete: " + dirsWithMaxSize.get(0).getSize());
    }

    public List<Directory> sort(List<Directory> list) {

        list.sort((o1, o2) -> {
                if (o2.getSize() < o1.getSize()) {
                    return 1;
                } else if (o1.getSize() < o2.getSize()) {
                    return -1;
                }
                return 0;
            }
        );

        return list;
    }
}
