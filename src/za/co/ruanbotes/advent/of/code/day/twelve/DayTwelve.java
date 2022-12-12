package za.co.ruanbotes.advent.of.code.day.twelve;

import za.co.ruanbotes.advent.of.code.util.Dijkstra;
import za.co.ruanbotes.advent.of.code.util.FileReader;

import java.util.*;

public class DayTwelve {
    private static final String EXAMPLE = "resources/za/co/ruanbotes/advent/of/code/day/twelve/example.txt";
    private static final String INPUT = "resources/za/co/ruanbotes/advent/of/code/day/twelve/input.txt";

    private Node source = null;

    public void run() {
        System.out.println("***** Part 1 *****");
        partOne(INPUT);
        System.out.println("==================");
        System.out.println("***** Part 2 *****");
        partTwo(INPUT);
        System.out.println("==================");
    }

    public void partOne(String path) {
        List<List<Character>> grid = FileReader.readFileToCharacterGrid(path);
        Node[][] nodes = getNodeGrid(grid);
        Graph graph = buildGraph(nodes);
        graph = Dijkstra.calculateShortestPathFromSource(graph, this.source);
        System.out.println("Distance: " + graph.getShortestPath());
    }

    public void partTwo(String path) {
        List<List<Character>> grid = FileReader.readFileToCharacterGrid(path);
        Node[][] nodes = getNodeGrid(grid);
        Graph graph = buildGraph(nodes);
        System.out.println("Distance: " + findStartingPointWithShortestPath(graph));

    }

    private int findStartingPointWithShortestPath(Graph graph) {
        int shortestPath = Integer.MAX_VALUE;

        for (Node node : graph.getNodes()) {
            if (node.getName().equals('S') || node.getName().equals('a')) {
                graph = Dijkstra.calculateShortestPathFromSource(graph, node);
                if (graph.getShortestPath() < shortestPath) {
                    shortestPath = graph.getShortestPath();
                }
            }
        }

        return shortestPath;
    }

    private Node[][] getNodeGrid(List<List<Character>> grid) {
        Node[][] nodes = new Node[grid.size()][grid.get(0).size()];

        for(int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                nodes[i][j] = new Node(grid.get(i).get(j));

                if (nodes[i][j].getName().equals('S')) {
                    this.source = nodes[i][j];
                }
            }
        }

        return nodes;
    }

    private Graph buildGraph(Node[][] nodes) {
        Graph graph = new Graph();

        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                if (i - 1 >= 0 && (nodes[i-1][j].getHeight() - nodes[i][j].getHeight() <= 1)) {
                    nodes[i][j].addDestination(nodes[i-1][j], 1);
                }

                if (i + 1 < nodes.length && (nodes[i+1][j].getHeight() - nodes[i][j].getHeight() <= 1)) {
                    nodes[i][j].addDestination(nodes[i+1][j], 1);
                }

                if (j - 1 >= 0 && (nodes[i][j-1].getHeight() - nodes[i][j].getHeight() <= 1)) {
                    nodes[i][j].addDestination(nodes[i][j-1], 1);
                }

                if (j + 1 < nodes[i].length && (nodes[i][j+1].getHeight() - nodes[i][j].getHeight() <= 1)) {
                    nodes[i][j].addDestination(nodes[i][j+1], 1);
                }

                graph.addNode(nodes[i][j]);
            }
        }

        return graph;
    }
}
