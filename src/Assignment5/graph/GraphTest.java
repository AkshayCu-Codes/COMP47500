package Assignment5.graph;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);

        graph.addEdge(n1, n2, 5, true);
        graph.addEdge(n2, n3, 3, true);

        graph.printGraph();
    }
}