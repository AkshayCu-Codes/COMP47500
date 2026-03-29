package Assignment3;

public class minHeap {
    private flight[] heap;
    private int size;
    
    // Set up the heap with a maximum capacity
    public minHeap(int capacity) {
        heap = new flight[capacity];
        size = 0;
    }
    // parent index of a given node
    private int parent(int i) {
        return (i-1)/2;
    }

    // left child
    private int leftChild(int i) {
        return 2*i+1;
    }

    // right child
    private int rightChild(int i) {
        return 2*i+2;
    }

    // Swaps two flights in the heap array
    private void swap(int i, int j) {
        flight temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // moves a flight UP until it is in the correct position
    private void siftUp(int i) {
        while (i > 0 && heap[i].priority < heap[parent(i)].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    
    // moves a flight DOWN until it is in the correct position
    private void siftDown(int i) {
        int smallest = i;
        int left  = leftChild(i);
        int right = rightChild(i);

        // check if left child has higher priority
        if (left < size && heap[left].priority < heap[smallest].priority)
            smallest = left;

        // check if right child has higher priority
        if (right < size && heap[right].priority < heap[smallest].priority)
            smallest = right;

        // if a smaller child was found swap 
        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    // adds a new flight and restores heap order
    public void addFlight(String flightCode, int fuelLevel, boolean emergency, boolean longHaul) {
        int priority = flight.calculatePriority(emergency, fuelLevel, longHaul);
        heap[size] = new flight(priority, flightCode, fuelLevel);
        siftUp(size);
        size++;
    }
    
    // removes and returns the highest priority flight
    public flight landNext() {
        flight next = heap[0];
        heap[0] = heap[size-1];
        size--;
        siftDown(0);
        return next;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}