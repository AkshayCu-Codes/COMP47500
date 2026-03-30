package Assignment3;

public class minHeap {
    private flight[] heap;
    private int size;

    public minHeap(int capacity) {
        heap = new flight[capacity];
        size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        flight temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void siftUp(int i) {
        while (i > 0 && heap[i].priority < heap[parent(i)].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left].priority < heap[smallest].priority)
            smallest = left;

        if (right < size && heap[right].priority < heap[smallest].priority)
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    public void addFlight(String flightCode, int fuelLevel, boolean emergency, boolean longHaul, String queuedAt, String landedAt) {
        int priority = flight.calculatePriority(emergency, fuelLevel, longHaul);
        heap[size] = new flight(priority, flightCode, fuelLevel, emergency, queuedAt, landedAt);
        siftUp(size);
        size++;
    }

    public flight landNext() {
        flight next = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return next;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}