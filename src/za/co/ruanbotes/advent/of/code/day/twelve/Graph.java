package za.co.ruanbotes.advent.of.code.day.twelve;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public int getShortestPath() {
        for (Node node : nodes) {
            if (node.getName().equals('E')) {
                return node.getDistance();
            }
        }

        return 0;
    }

    public Set<Node> getNodes() {
        return this.nodes;
    }
}
