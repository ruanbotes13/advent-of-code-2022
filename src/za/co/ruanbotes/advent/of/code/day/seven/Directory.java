package za.co.ruanbotes.advent.of.code.day.seven;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    List<Directory> directories = new ArrayList<>();
    List<File> files = new ArrayList<>();
    private String name;
    private int size;
    Directory parent;

    Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.size = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public void addDirectory(String name) {
        if (this.getDirectoryByName(name) == null) {
            this.directories.add(
                new Directory(name, this)
            );
        }
    }

    public void addFile(String name, int size) {
        this.files.add(
            new File(name, size, this)
        );
    }

    public Directory getDirectoryByName(String nameToFind) {
        List<Directory> directoryList = this.directories
            .stream()
            .filter(directory -> directory.name.equals(nameToFind))
            .toList();

        if (directoryList.isEmpty()) {
            return null;
        }

        return directoryList.get(0);
    }

    public void calculateDirectorySize() {
        int dirSize = 0;

        for (int i = 0; i < this.files.size(); i++) {
            dirSize += this.files.get(i).getSize();
        }

        for (int i = 0; i < directories.size(); i++) {
            directories.get(i).calculateDirectorySize();
            dirSize += directories.get(i).getSize();
        }

        this.size = dirSize;
    }

    public int getDirectorySize() {
        int dirSize = 0;

        for (int i = 0; i < this.files.size(); i++) {
            dirSize += this.files.get(i).getSize();
        }

        for (int i = 0; i < this.directories.size(); i++) {
            dirSize += this.directories.get(i).getDirectorySize();
        }

        return dirSize;
    }

    public List<Directory> getDirsWithMaxSize(int maxSize) {
        List<Directory> directoryList = new ArrayList<>();

        for (int i = 0; i < this.directories.size(); i++) {
            if (directories.get(i).getSize() <= maxSize) {
                directoryList.add(directories.get(i));
                directoryList.addAll(directories.get(i).getDirsWithMaxSize(maxSize));
            } else {
                directoryList.addAll(directories.get(i).getDirsWithMaxSize(maxSize));
            }
        }

        return directoryList;
    }

    public List<Directory> getDirsWithMinSize(int minSize) {
        List<Directory> directoryList = new ArrayList<>();

        for (int i = 0; i < this.directories.size(); i++) {
            if (directories.get(i).getSize() >= minSize) {
                directoryList.add(directories.get(i));
                directoryList.addAll(directories.get(i).getDirsWithMinSize(minSize));
            } else {
                directoryList.addAll(directories.get(i).getDirsWithMinSize(minSize));
            }
        }

        return directoryList;
    }
}
