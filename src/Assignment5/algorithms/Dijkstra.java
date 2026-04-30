package Assignment5.algorithms;

import Assignment5.graph.*;
import java.util.*;

public class Dijkstra {

    private static int visitedNodes = 0;

    public static int getVisitedNodes() {
        return visitedNodes;
    }

    public static List<Node> findShortestPath(Graph graph, Node start, Node goal) {

        visitedNodes = 0;

        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();

        // select node with minimum distance
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(distances::get)
        );

        for (Node node : graph.getAdjList().keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            visitedNodes++;

            if (current.equals(goal)) {
                return buildPath(previous, start, goal);
            }

            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.getTarget();

                int newDistance = distances.get(current) + edge.getWeight();

                // relax edge if shorter path found
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);

                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    public static int getPathCost(Graph graph, List<Node> path) {
        int cost = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            Node current = path.get(i);
            Node next = path.get(i + 1);

            for (Edge edge : graph.getNeighbors(current)) {
                if (edge.getTarget().equals(next)) {
                    cost += edge.getWeight();
                    break;
                }
            }
        }

        return cost;
    }

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