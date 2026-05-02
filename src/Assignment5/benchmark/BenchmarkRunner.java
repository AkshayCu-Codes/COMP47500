package Assignment5.benchmark;

import Assignment5.algorithms.BFS;
import Assignment5.algorithms.Dijkstra;
import Assignment5.graph.Graph;
import Assignment5.graph.Node;
import Assignment5.utils.GraphGenerator;


import java.util.List;

public class BenchmarkRunner {

    public static void main(String[] args) {

        int[] sizes = {10, 50, 100, 150, 250, 500, 750, 1000};

        System.out.println("BFS Benchmark Results");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s %-12s %-12s %-20s%n",
                "Nodes", "ExtraEdges", "PathLength", "ExecutionTime(ns)");
        System.out.println("---------------------------------------------------------------");

        for (int size : sizes) {
            runBFSBenchmark(size);
        }

        System.out.println("---------------------------------------------------------------");
    }

    private static void runBFSBenchmark(int nodeCount) {
        int extraEdges = nodeCount / 2;

        Graph graph = GraphGenerator.generateGraph(nodeCount, extraEdges);

        Node start = new Node(1);
        Node goal = new Node(nodeCount);

        // JVM warm-up
        for (int i = 0; i < 5; i++) {
            BFS.findPath(graph, start, goal);
        }

        // actual measurement
        long startTime = System.nanoTime();
        List<Node> path = BFS.findPath(graph, start, goal);
        long endTime = System.nanoTime();

        System.out.printf("%-10d %-12d %-12d %-20d%n",
                nodeCount,
                extraEdges,
                path.size(),
                (endTime - startTime));
    }
    
    private static void runBenchmark(int nodeCount) {
        int extraEdges = nodeCount / 2;

        Graph graph = GraphGenerator.generateGraph(nodeCount, extraEdges);

        Node start = new Node(1);
        Node goal = new Node(nodeCount);

        // JVM warm-up
        for (int i = 0; i < 5; i++) {
            BFS.findPath(graph, start, goal);
            Dijkstra.findShortestPath(graph, start, goal);
        }

        // BFS timing
        long bfsStart = System.nanoTime();
        List<Node> bfsPath = BFS.findPath(graph, start, goal);
        long bfsEnd = System.nanoTime();

        int bfsCost = Dijkstra.getPathCost(graph, bfsPath);

        // Dijkstra timing
        long dijStart = System.nanoTime();
        List<Node> dijPath = Dijkstra.findShortestPath(graph, start, goal);
        long dijEnd = System.nanoTime();

        int dijCost = Dijkstra.getPathCost(graph, dijPath);

        System.out.printf("%-8d %-12d %-15d %-15d %-15d %-15d%n",
                nodeCount,
                extraEdges,
                (bfsEnd - bfsStart),
                (dijEnd - dijStart),
                bfsCost,
                dijCost);
    }

}