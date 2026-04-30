package Assignment5.utils;

import Assignment5.graph.Graph;
import Assignment5.graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {

    public static Graph generateGraph(int nodeCount, int extraEdges) {
        Graph graph = new Graph();
        List<Node> nodes = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 1; i <= nodeCount; i++) {
            Node node = new Node(i);
            nodes.add(node);
            graph.addNode(node);
        }

        // make sure the graph is connected
        for (int i = 0; i < nodeCount - 1; i++) {
            int weight = random.nextInt(10) + 1;
            graph.addEdge(nodes.get(i), nodes.get(i + 1), weight, true);
        }

        // add extra random roads
        for (int i = 0; i < extraEdges; i++) {
            Node from = nodes.get(random.nextInt(nodeCount));
            Node to = nodes.get(random.nextInt(nodeCount));

            if (!from.equals(to)) {
                int weight = random.nextInt(10) + 1;
                graph.addEdge(from, to, weight, true);
            }
        }

        return graph;
    }
}