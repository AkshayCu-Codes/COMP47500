package Assignment5.benchmark;

import Assignment5.algorithms.BFS;
import Assignment5.algorithms.Dijkstra;
import Assignment5.algorithms.AStar;
import Assignment5.graph.Graph;
import Assignment5.graph.Node;
import Assignment5.utils.GraphGenerator;

import java.util.List;

public class BenchmarkRunner {

    public static void main(String[] args) {

        int[] sizes = {10, 50, 100, 150, 250, 500, 750, 1000};

        String line = getLine(155);

        System.out.println("Table: Performance Comparison of BFS, Dijkstra, and A* Algorithms\n");
        System.out.println(line);

        System.out.printf("%-6s | %-5s | %-14s | %-18s | %-12s | %-8s | %-14s | %-8s | %-12s | %-18s | %-12s%n",
                "Nodes", "Edges", "BFS Time (ms)", "Dijkstra Time (ms)", "A* Time (ms)",
                "BFS Cost", "Dijkstra Cost", "A* Cost",
                "BFS Visited", "Dijkstra Visited", "A* Visited");

        System.out.println(line);

        // data rows
        for (int size : sizes) {
            runBenchmark(size);
        }
        System.out.println(line);
    }

    private static String getLine(int length) {
        return "-".repeat(length);
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
            AStar.findPath(graph, start, goal);
        }

        long bfsStart = System.nanoTime();
        List<Node> bfsPath = BFS.findPath(graph, start, goal);
        long bfsEnd = System.nanoTime();

        long dijStart = System.nanoTime();
        List<Node> dijPath = Dijkstra.findShortestPath(graph, start, goal);
        long dijEnd = System.nanoTime();

        long astarStart = System.nanoTime();
        List<Node> astarPath = AStar.findPath(graph, start, goal);
        long astarEnd = System.nanoTime();

        int bfsCost = Dijkstra.getPathCost(graph, bfsPath);
        int dijCost = Dijkstra.getPathCost(graph, dijPath);
        int astarCost = Dijkstra.getPathCost(graph, astarPath);

        System.out.printf("%-6d | %-5d | %-14.4f | %-18.4f | %-12.4f | %-8d | %-14d | %-8d | %-12d | %-18d | %-12d%n",
                nodeCount,
                extraEdges,
                (bfsEnd - bfsStart) / 1_000_000.0,
                (dijEnd - dijStart) / 1_000_000.0,
                (astarEnd - astarStart) / 1_000_000.0,
                bfsCost,
                dijCost,
                astarCost,
                BFS.getVisitedNodes(),
                Dijkstra.getVisitedNodes(),
                AStar.getVisitedNodes());
    }
}