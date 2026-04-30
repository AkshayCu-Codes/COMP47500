package Assignment5.visual;

import Assignment5.graph.Edge;
import Assignment5.graph.Graph;
import Assignment5.graph.Node;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphVisualizer extends JPanel {

    private Graph graph;
    private List<Node> path;
    private Map<Node, Point> positions;

    public GraphVisualizer(Graph graph, List<Node> path) {
        this.graph = graph;
        this.path = path;
        this.positions = new HashMap<>();

        setPreferredSize(new Dimension(650, 500));
        setNodePositions();
    }

    private void setNodePositions() {
        // Clean layout for 5-node route example
        positions.put(new Node(1), new Point(80, 250));
        positions.put(new Node(2), new Point(270, 120));
        positions.put(new Node(3), new Point(270, 380));
        positions.put(new Node(4), new Point(470, 380));
        positions.put(new Node(5), new Point(520, 250));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawEdges(g);
        drawPath(g);
        drawNodes(g);
    }

    private void drawEdges(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        Set<String> drawnEdges = new HashSet<>();

        for (Node node : graph.getAdjList().keySet()) {
            Point from = positions.get(node);

            for (Edge edge : graph.getNeighbors(node)) {
                Node target = edge.getTarget();
                Point to = positions.get(target);

                String edgeKey = Math.min(node.getId(), target.getId()) + "-" +
                                 Math.max(node.getId(), target.getId());

                if (from != null && to != null && !drawnEdges.contains(edgeKey)) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawLine(from.x, from.y, to.x, to.y);

                    int midX = (from.x + to.x) / 2;
                    int midY = (from.y + to.y) / 2;

                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(edge.getWeight()), midX, midY);

                    drawnEdges.add(edgeKey);
                }
            }
        }
    }

    private void drawPath(Graphics g) {
        if (path == null || path.size() < 2) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(4));

        for (int i = 0; i < path.size() - 1; i++) {
            Point from = positions.get(path.get(i));
            Point to = positions.get(path.get(i + 1));

            if (from != null && to != null) {
                g2.drawLine(from.x, from.y, to.x, to.y);
            }
        }

        g2.setStroke(new BasicStroke(1));
    }

    private void drawNodes(Graphics g) {
        for (Node node : graph.getAdjList().keySet()) {
            Point point = positions.get(node);

            if (point != null) {
                if (path.contains(node)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLUE);
                }

                g.fillOval(point.x - 16, point.y - 16, 32, 32);

                g.setColor(Color.BLACK);
                g.drawString(node.toString(), point.x - 22, point.y - 25);
            }
        }
    }

    public static void showGraph(Graph graph, List<Node> path) {
        JFrame frame = new JFrame("BFS Emergency Route Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphVisualizer(graph, path));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}