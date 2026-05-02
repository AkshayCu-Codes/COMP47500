package Assignment5.utils;

import Assignment5.graph.Graph;
import Assignment5.graph.Node;

import java.util.*;

public class GraphGenerator {

    public static Graph generateGraph(int nodeCount, int extraEdges) {

        Graph graph = new Graph();
        List<Node> nodes = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 1; i <= nodeCount; i++) {
            Node node = new Node(i, random.nextInt(100), random.nextInt(100));
            nodes.add(node);
            graph.addNode(node);
        }

        nodes.sort(Comparator.comparingInt(Node::getX));

        for (int i = 0; i < nodes.size() - 1; i++) {
            addRoad(graph, nodes.get(i), nodes.get(i + 1), random);
        }

        connectNearbyNodes(graph, nodes, 2, random);
        addExtraEdges(graph, nodes, extraEdges / 4, random);

        return graph;
    }

    private static void connectNearbyNodes(Graph graph, List<Node> nodes, int neighbours, Random random) {

        for (Node node : nodes) {
            nodes.stream()
                    .filter(other -> !other.equals(node))
                    .sorted(Comparator.comparingInt(other -> baseDistance(node, other)))
                    .limit(neighbours)
                    .forEach(other -> addRoad(graph, node, other, random));
        }
    }

    private static void addExtraEdges(Graph graph, List<Node> nodes, int extraEdges, Random random) {

        for (int i = 0; i < extraEdges; i++) {

            Node from = nodes.get(random.nextInt(nodes.size()));

            Node to = nodes.stream()
                    .filter(other -> !other.equals(from))
                    .sorted(Comparator.comparingInt(other -> baseDistance(from, other)))
                    .skip(random.nextInt(3))
                    .findFirst()
                    .orElse(null);

            if (to != null) {
                addRoad(graph, from, to, random);
            }
        }
    }

    private static void addRoad(Graph graph, Node from, Node to, Random random) {
        int weight = weightedDistance(from, to, random);
        graph.addEdge(from, to, weight, true);
    }

    // stable distance used only for sorting nearby nodes
    private static int baseDistance(Node a, Node b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();

        return Math.max(1, (int) Math.round(Math.sqrt(dx * dx + dy * dy)));
    }

    // edge weight includes variation to simulate traffic/road conditions
    private static int weightedDistance(Node a, Node b, Random random) {
        int variation = random.nextInt(30);
        return baseDistance(a, b) + variation;
    }
}
