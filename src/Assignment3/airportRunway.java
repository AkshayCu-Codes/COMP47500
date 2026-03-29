package Assignment3;

public class airportRunway {

    static void printFlight(int slot, flight f) {
        System.out.println("\nSlot " + slot + ": CLEARED TO LAND");
        System.out.println("  Flight   : " + f.flightCode);
        System.out.println("  Priority : " + f.priority);
        System.out.println("  Fuel     : " + f.fuelLevel + "% remaining");
    }

    public static void main(String[] args) {

    	minHeap runway = new minHeap(12);

        runway.addFlight("RYR101 - Dublin to London",    45, false, false);
        runway.addFlight("EI404  - Emergency Landing",    8, true,  false);
        runway.addFlight("FR202  - Amsterdam to Dublin", 62, false, false);
        runway.addFlight("AA789  - Critical Fuel",       15, false, false);
        runway.addFlight("BA317  - New York to Dublin",  50, false, true);
        runway.addFlight("LH324  - Emergency Low Fuel",  25, true,  false);
        runway.addFlight("TK891  - Critical Fuel Only",   7, false, false);

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