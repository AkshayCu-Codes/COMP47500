package Assignment5.algorithms;

import Assignment5.graph.*;
import java.util.*;

public class BFS {

    // BFS
    public void traverse(Graph graph, Node start) {

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        // start
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            // take the next node to process
            Node current = queue.poll();
            System.out.print(current + " ");
            // explore all connected neighbours
            for (Edge edge : graph.getAdjList().get(current)) {
                Node neighbor = edge.getTarget();
                // only visit unvisited nodes
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}