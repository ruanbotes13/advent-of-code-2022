package za.co.ruanbotes.advent.of.code.day.twelve;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    private Character name;
    private int height;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(Character name) {
        this.name = name;
        if (name.equals('S')) {
            this.height = 'a';
        } else if (name.equals('E')) {
            this.height = 'z';
        } else {
            this.height = name;
        }
    }

    public Integer getDistance() {
        return this.distance;
    }

    public int getHeight() {
        return this.height;
    }

    public Character getName() {
        return this.name;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return this.shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return this.adjacentNodes;
    }
}
