package Assignment5.graph;

import Assignment5.algorithms.BFS;
import Assignment5.algorithms.Dijkstra;
import Assignment5.algorithms.AStar;
import Assignment5.visual.GraphVisualizer;

import java.util.List;

public class GraphTest {
    public static void main(String[] args) {

        Graph graph = new Graph();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        graph.addEdge(n1, n2, 4, true);
        graph.addEdge(n2, n5, 6, true);
        graph.addEdge(n1, n3, 2, true);
        graph.addEdge(n3, n4, 2, true);
        graph.addEdge(n4, n5, 2, true);

        graph.printGraph();

        // run algorithms
        List<Node> bfsPath = BFS.findPath(graph, n1, n5);
        List<Node> dijPath = Dijkstra.findShortestPath(graph, n1, n5);
        List<Node> astarPath = AStar.findPath(graph, n1, n5);

        // print results
        System.out.println("\n--- BFS ---");
        System.out.println("Path: " + bfsPath);
        System.out.println("Cost: " + Dijkstra.getPathCost(graph, bfsPath));
        System.out.println("Visited Nodes: " + BFS.getVisitedNodes());

        System.out.println("\n--- Dijkstra ---");
        System.out.println("Path: " + dijPath);
        System.out.println("Cost: " + Dijkstra.getPathCost(graph, dijPath));
        System.out.println("Visited Nodes: " + Dijkstra.getVisitedNodes());

        System.out.println("\n--- A* ---");
        System.out.println("Path: " + astarPath);
        System.out.println("Cost: " + Dijkstra.getPathCost(graph, astarPath));
        System.out.println("Visited Nodes: " + AStar.getVisitedNodes());

        // visualize
        GraphVisualizer.showGraph(graph, bfsPath, "BFS Path");
        GraphVisualizer.showGraph(graph, dijPath, "Dijkstra Path");
        GraphVisualizer.showGraph(graph, astarPath, "A* Path");
    }
}