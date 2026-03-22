package Assignment3;

public class airportRunway {

    static void printFlight(int slot, flight f) {
        System.out.println("\nSlot " + slot + ": CLEARED TO LAND");
        System.out.println("  Flight   : " + f.flightCode);
        System.out.println("  Priority : " + f.priority);
        System.out.println("  Fuel     : " + f.fuelLevel + "% remaining");
    }

    public static void main(String[] args) {

        // minHeap replaces the old array scan - now O(log n) instead of O(n)
        minHeap runway = new minHeap(10);

        runway.addFlight(3, "RYR101 - Dublin to London",    45);
        runway.addFlight(1, "EI404  - Emergency Landing",    8);
        runway.addFlight(5, "FR202  - Amsterdam to Dublin", 62);
        runway.addFlight(2, "AA789  - Critical Fuel",       15);
        runway.addFlight(4, "BA317  - New York to Dublin",  50);

        System.out.println("========================================");
        System.out.println("  DUBLIN AIRPORT - LANDING QUEUE       ");
        System.out.println("========================================");

        int slot = 1;
        while (!runway.isEmpty()) {
            printFlight(slot, runway.landNext());
            slot++;
        }

        System.out.println("\n  All flights landed safely.");
        System.out.println("========================================");
    }
}