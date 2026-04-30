package Assignment5.graph;

import Assignment5.algorithms.BFS;
import Assignment5.algorithms.Dijkstra;
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

        List<Node> bfsPath = BFS.findPath(graph, n1, n5);// find path from n1 to n5 using BFS
        List<Node> dijkstraPath = Dijkstra.findShortestPath(graph, n1, n5);

       
        System.out.println("\nBFS Path: " + bfsPath);
        
        System.out.println("Dijkstra Path: " + dijkstraPath);
        System.out.println("Dijkstra Cost: " + Dijkstra.getPathCost(graph, dijkstraPath));

        GraphVisualizer.showGraph(graph, bfsPath, "BFS Path");
        GraphVisualizer.showGraph(graph, dijkstraPath, "Dijkstra Path");
    }
}