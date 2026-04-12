package Assignment4;

public class HashTable {

    private final boolean[] table;
    private final int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table    = new boolean[capacity];  // bit per slot
    }

    public void set(int index) {
        table[Math.abs(index % capacity)] = true;
    }

    public boolean get(int index) {
        return table[Math.abs(index % capacity)];
    }

    public void clear() {
        java.util.Arrays.fill(table, false);
    }

    public int capacity() { return capacity; }
}