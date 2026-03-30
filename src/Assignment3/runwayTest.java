package Assignment3;

import java.util.Random;

public class runwayTest {
    static flight[] flights = new flight[1100000];
    static int count = 0;

    static void addFlightArray(int priority, String code, int fuelLevel, boolean emergency) {
        flights[count] = new flight(priority, code, fuelLevel, emergency, "", "");
        count++;
    }

    static flight getNextFlight() {
        int minIndex = 0;
        for (int i = 1; i < count; i++) {
            if (flights[i].priority < flights[minIndex].priority) {
                minIndex = i;
            }
        }
        flight next = flights[minIndex];
        for (int i = minIndex; i < count - 1; i++) {
            flights[i] = flights[i + 1];
        }
        count--;
        return next;
    }

    static void printPriorityGuide() {
        System.out.println("\nPriority Rules:");
        System.out.println("1 = emergency + fuel below 10%");
        System.out.println("2 = emergency");
        System.out.println("3 = fuel below 10%");
        System.out.println("4 = fuel below 30%");
        System.out.println("5 = long-haul");
        System.out.println("6 = normal");
    }

    static void printSampleFlights() {
        Random r = new Random(42);
        System.out.println("\nSample Priority View:");
        System.out.printf("%-8s %-6s %-10s %-9s %-8s%n", "Flight", "Fuel", "Emergency", "LongHaul", "Priority");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            int fuelLevel = r.nextInt(100) + 1;
            boolean emergency = fuelLevel < 10;
            boolean longHaul = r.nextBoolean();
            int priority = flight.calculatePriority(emergency, fuelLevel, longHaul);
            System.out.printf("%-8s %-6d %-10s %-9s %-8d%n", "FL" + i, fuelLevel, emergency ? "Yes" : "No", longHaul ? "Yes" : "No", priority);
        }
    }

    private static void runBenchmark(int flightCount, boolean print) {
        minHeap heap = new minHeap(flightCount);
        count = 0;
        Random r = new Random(42);
        for (int i = 0; i < flightCount; i++) {
            int fuelLevel = r.nextInt(100) + 1;
            boolean emergency = fuelLevel < 10;
            boolean longHaul = r.nextBoolean();
            int priority = flight.calculatePriority(emergency, fuelLevel, longHaul);
            heap.addFlight("FL" + i, fuelLevel, emergency, longHaul, "", "");
            addFlightArray(priority, "FL" + i, fuelLevel, emergency);
        }

        long startHeap = System.nanoTime();
        while (!heap.isEmpty()) {
            heap.landNext();
        }
        long endHeap = System.nanoTime();

        long startArray = System.nanoTime();
        while (count > 0) {
            getNextFlight();
        }
        long endArray = System.nanoTime();

        if (print) {
            System.out.printf("%-15d %-18d %-18d%n", flightCount, (endHeap - startHeap), (endArray - startArray));
        }
    }

    public static void main(String[] args) {
        runBenchmark(200, false);
        int[] scales = {30, 90, 180, 200};

        System.out.println("============================================================");
        System.out.println("RUNWAY DISPATCH BENCHMARK");
        System.out.println("============================================================");

        printPriorityGuide();
        printSampleFlights();

        System.out.println("\nBenchmark Results:");
        System.out.printf("%-15s %-18s %-18s%n", "Airport Scale", "MinHeap(ns)", "Array Scan(ns)");
        System.out.println("------------------------------------------------------------");
        for (int scale : scales) {
            runBenchmark(scale, true);
        }
        System.out.println("------------------------------------------------------------");
    }
}