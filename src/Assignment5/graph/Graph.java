package Assignment5.graph;

import java.util.*;

public class Graph {
    private Map<Node, List<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to, int weight, boolean bidirectional) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            throw new IllegalArgumentException("Both nodes must exist in graph");
        }

        adjList.get(from).add(new Edge(to, weight));

        if (bidirectional) {
            adjList.get(to).add(new Edge(from, weight));
        }
    }
    public Node getNodeById(int id) {
        for (Node node : adjList.keySet()) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    public Map<Node, List<Edge>> getAdjList() {
        return adjList;
    }
    public List<Edge> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }
    public void printGraph() {
        for (Node node : adjList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge edge : adjList.get(node)) {
                System.out.print(edge.getTarget() + "(" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }
}
