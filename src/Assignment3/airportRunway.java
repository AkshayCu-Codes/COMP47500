package Assignment3;

//NOTE: getNextFlight() scans the entire array every time a flight is dispatched
//This is O(n) - with 100s of flights this gets slow
//Switching to MinHeap which gives O(log n) insert and dispatch
//MinHeap always keeps lowest priority flight at top - no scanning needed

public class airportRunway {

    static flight[] flights = new flight[10];

    static int count = 0;

    // Add a new flight to the waiting
    static void addFlight(int priority, String flightCode, int fuelLevel) {
        flights[count] = new flight(priority, flightCode, fuelLevel);
        count++;
    }

    // Scans the entire array to find the flight with lowest priority number
    // This is O(n) - checks every single flight each time
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

    public static void main(String[] args) {
        
        // Add flights to the waiting queue
        addFlight(3, "RYR101 - Dublin to London",    45);
        addFlight(1, "EI404  - Emergency Landing",    8);
        addFlight(5, "FR202  - Amsterdam to Dublin", 62);
        addFlight(2, "AA789  - Critical Fuel",       15);
        addFlight(4, "BA317  - New York to Dublin",  50);

        System.out.println("========================================");
        System.out.println("  DUBLIN AIRPORT - LANDING QUEUE       ");
        System.out.println("========================================");
        
        // Keep dispatching flights until queue is empty
        int slot = 1;
        while (count > 0) {
            flight f = getNextFlight();
            System.out.println("\nSlot " + slot + ": CLEARED TO LAND");
            System.out.println("  Flight   : " + f.flightCode);
            System.out.println("  Priority : " + f.priority);
            System.out.println("  Fuel     : " + f.fuelLevel + "% remaining");
            slot++;
        }

        System.out.println("\n  All flights landed safely.");
        System.out.println("========================================");
    }
}
