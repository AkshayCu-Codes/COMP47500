package Assignment3;

public class airportRunway {

    static void printFlight(int slot, flight f) {
        System.out.println("\nSlot " + slot + ":");
        System.out.println("  Flight   : " + f.flightCode);
        System.out.println("  Priority : " + f.priority);
        System.out.println("  Fuel     : " + f.fuelLevel + "% remaining");
        System.out.println("  Queued   : " + f.queuedAt);
        System.out.println("  Landed   : " + f.landedAt);
    }

    public static void main(String[] args) {

        minHeap runway = new minHeap(12);

        // addFlight(flightCode, fuelLevel, emergency, longHaul, queuedAt, landedAt)
        runway.addFlight("EI404  - New York to Dublin",      8, true,  true,  "14:05", "14:11");
        runway.addFlight("LH324  - Frankfurt to Dublin",    25, true,  false, "14:12", "14:21");
        runway.addFlight("TK891  - Istanbul to Dublin",      7, false, true,  "14:19", "14:26");
        runway.addFlight("AA789  - Boston to Dublin",       15, false, true,  "14:27", "14:38");
        runway.addFlight("BA317  - London to Dublin",       50, false, false, "14:35", "14:51");
        runway.addFlight("FR202  - Amsterdam to Dublin",    62, false, false, "14:41", "15:02");
        runway.addFlight("RYR101 - Madrid to Dublin",       45, false, false, "14:48", "15:10");

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