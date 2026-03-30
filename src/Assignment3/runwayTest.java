package Assignment3;

import java.util.*;

public class runwayTest {

    // array scan - brute force O(n)
    static flight[] flights = new flight[1100000];
    static int count = 0;

    // adds a flight directly to the array without any ordering
    static void addFlightArray(int priority, String code, int fuelLevel) {
        flights[count] = new flight(priority, code, fuelLevel, "", "");
        count++;
    }

    // scans entire array every time to find the lowest priority flight - O(n)
    static flight getNextFlight() {
        int minIndex = 0;
        for (int i = 1; i < count; i++) {
            if (flights[i].priority < flights[minIndex].priority)
                minIndex = i;
        }
        flight next = flights[minIndex];
        for (int i = minIndex; i < count - 1; i++)
            flights[i] = flights[i + 1];
        count--;
        return next;
    }

    private static void runBenchmark(int flightCount, boolean print) {

        minHeap heap = new minHeap(flightCount);
        count = 0;

        // same seed ensures both heap and array get identical flights
        Random r = new Random(42);
        for (int i = 0; i < flightCount; i++) {
            int fuelLevel     = r.nextInt(90) + 10;
            boolean emergency = fuelLevel < 10;
            boolean longHaul  = fuelLevel > 50;
            int priority      = flight.calculatePriority(emergency, fuelLevel, longHaul);
            heap.addFlight("FL" + i, fuelLevel, emergency, longHaul, "", "");
            addFlightArray(priority, "FL" + i, fuelLevel);
        }

        // minHeap dispatch time
        long startHeap = System.nanoTime();
        while (!heap.isEmpty()) heap.landNext();
        long endHeap = System.nanoTime();

        // array scan dispatch time
        long startArray = System.nanoTime();
        while (count > 0) getNextFlight();
        long endArray = System.nanoTime();

        if (print) {
            System.out.printf("%-15s | %-20s | %-20s%n",
                flightCount,
                (endHeap  - startHeap),
                (endArray - startArray)
            );
        }
    }

    public static void main(String[] args) {

        // warmup run
        runBenchmark(200, false);

        // flights per hour at major airports
        int[] scales = {30, 90, 180, 200};

        System.out.printf("%-15s | %-20s | %-20s%n",
            "Airport Scale", "MinHeap Dispatch(ns)", "Array Dispatch(ns)");
        System.out.println("-".repeat(60));

        for (int scale : scales) {
            runBenchmark(scale, true);
        }

        System.out.println("-".repeat(60));
    }
}