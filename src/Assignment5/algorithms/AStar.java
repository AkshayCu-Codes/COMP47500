package Assignment5.algorithms;

import Assignment5.graph.*;
import java.util.*;

public class AStar {

    public static List<Node> findPath(Graph graph, Node start, Node goal) {

        Map<Node, Integer> gScore = new HashMap<>();
        Map<Node, Integer> fScore = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();

        PriorityQueue<Node> openSet = new PriorityQueue<>(
                Comparator.comparingInt(fScore::get)
        );

        for (Node node : graph.getAdjList().keySet()) {
            gScore.put(node, Integer.MAX_VALUE);
            fScore.put(node, Integer.MAX_VALUE);
        }

        gScore.put(start, 0);
        fScore.put(start, heuristic(start, goal));
        openSet.add(start);

        while (!openSet.isEmpty()) {

            Node current = openSet.poll();

            if (current.equals(goal)) {
                return buildPath(previous, start, goal);
            }

            for (Edge edge : graph.getNeighbors(current)) {
                Node neighbor = edge.getTarget();
                int newScore = gScore.get(current) + edge.getWeight();

                // update only if better route found
                if (newScore < gScore.get(neighbor)) {
                    previous.put(neighbor, current);
                    gScore.put(neighbor, newScore);
                    fScore.put(neighbor, newScore + heuristic(neighbor, goal));

                    openSet.remove(neighbor);
                    openSet.add(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    // simple heuristic 
    private static int heuristic(Node current, Node goal) {
        return Math.abs(current.getId() - goal.getId());
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