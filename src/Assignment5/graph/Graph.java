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

    public void addEdge(Node from, Node to, int weight) {
        adjList.get(from).add(new Edge(to, weight));
    }

    public Map<Node, List<Edge>> getAdjList() {
        return adjList;
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
