package Assignment5.graph;

public class Node {
    private int id;
    private int x;
    private int y;

    public Node(int id) {
        this(id, 0, 0);
    }

    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Node-" + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
