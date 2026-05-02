package Assignment5.benchmark;

import Assignment5.algorithms.BFS;
import Assignment5.algorithms.Dijkstra;
import Assignment5.algorithms.AStar;
import Assignment5.graph.Graph;
import Assignment5.graph.Node;
import Assignment5.utils.GraphGenerator;

import java.util.List;

public class BenchmarkRunner {

    // warm-up runs to stabilise JVM
    private static final int WARMUP_RUNS = 3;

    // actual runs used for averaging
    private static final int MEASURED_RUNS = 5;

    public static void main(String[] args) {

        int[] sizes = {10, 50, 100, 150, 250, 500, 750, 1000};

        String line = "-".repeat(155);

        System.out.println("Table: Performance Comparison of BFS, Dijkstra, and A* Algorithms");
        System.out.println("Average of " + MEASURED_RUNS + " measured runs after " + WARMUP_RUNS + " warm-up runs\n");

        System.out.println(line);

        // table header
        System.out.printf("%-6s | %-5s | %-14s | %-18s | %-12s | %-8s | %-14s | %-8s | %-12s | %-18s | %-12s%n",
                "Nodes", "Edges", "BFS Time (ms)", "Dijkstra Time (ms)", "A* Time (ms)",
                "BFS Cost", "Dijkstra Cost", "A* Cost",
                "BFS Visited", "Dijkstra Visited", "A* Visited");

        System.out.println(line);

        // run benchmark for different graph sizes
        for (int size : sizes) {
            runBenchmark(size);
        }

        System.out.println(line);
    }

    private static void runBenchmark(int nodeCount) {

        // control graph density
        int extraEdges = nodeCount / 4;

        double bfsTotalTime = 0;
        double dijTotalTime = 0;
        double astarTotalTime = 0;

        int bfsCost = 0;
        int dijCost = 0;
        int astarCost = 0;

        int bfsVisited = 0;
        int dijVisited = 0;
        int astarVisited = 0;

        // repeat runs for stable average
        for (int run = 0; run < MEASURED_RUNS; run++) {

            Graph graph = GraphGenerator.generateGraph(nodeCount, extraEdges);

            // get actual nodes from graph (important for A*)
            Node start = graph.getNodeById(1);
            Node goal = graph.getNodeById(nodeCount);

            // JVM warm-up (not measured)
            for (int i = 0; i < WARMUP_RUNS; i++) {
                BFS.findPath(graph, start, goal);
                Dijkstra.findShortestPath(graph, start, goal);
                AStar.findPath(graph, start, goal);
            }

            // measure BFS
            long bfsStart = System.nanoTime();
            List<Node> bfsPath = BFS.findPath(graph, start, goal);
            long bfsEnd = System.nanoTime();

            // measure Dijkstra
            long dijStart = System.nanoTime();
            List<Node> dijPath = Dijkstra.findShortestPath(graph, start, goal);
            long dijEnd = System.nanoTime();

            // measure A*
            long astarStart = System.nanoTime();
            List<Node> astarPath = AStar.findPath(graph, start, goal);
            long astarEnd = System.nanoTime();

            // accumulate execution time (ms)
            bfsTotalTime += (bfsEnd - bfsStart) / 1_000_000.0;
            dijTotalTime += (dijEnd - dijStart) / 1_000_000.0;
            astarTotalTime += (astarEnd - astarStart) / 1_000_000.0;

            // compute path costs
            bfsCost = Dijkstra.getPathCost(graph, bfsPath);
            dijCost = Dijkstra.getPathCost(graph, dijPath);
            astarCost = Dijkstra.getPathCost(graph, astarPath);

            // track nodes explored
            bfsVisited = BFS.getVisitedNodes();
            dijVisited = Dijkstra.getVisitedNodes();
            astarVisited = AStar.getVisitedNodes();
        }

        // print averaged results
        System.out.printf("%-6d | %-5d | %-14.4f | %-18.4f | %-12.4f | %-8d | %-14d | %-8d | %-12d | %-18d | %-12d%n",
                nodeCount,
                extraEdges,
                bfsTotalTime / MEASURED_RUNS,
                dijTotalTime / MEASURED_RUNS,
                astarTotalTime / MEASURED_RUNS,
                bfsCost,
                dijCost,
                astarCost,
                bfsVisited,
                dijVisited,
                astarVisited);
    }
}
