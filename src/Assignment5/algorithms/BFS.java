package Assignment5.algorithms;

import Assignment5.graph.*;
import java.util.*;

public class BFS {

    private static int visitedNodes = 0;

    public static int getVisitedNodes() {
        return visitedNodes;
    }

    public static List<Node> findPath(Graph graph, Node start, Node goal) {

        visitedNodes = 0;

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> previous = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            visitedNodes++;

            if (current.equals(goal)) {
                return buildPath(previous, start, goal);
            }

            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.getTarget();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    // rebuild path from goal → start
    private static List<Node> buildPath(Map<Node, Node> previous, Node start, Node goal) {
        List<Node> path = new ArrayList<>();
        Node current = goal;

        while (current != null) {
            path.add(current);
            if (current.equals(start)) break;
            current = previous.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}