package Assignment3;

public class flight implements Comparable<flight> {

    int priority;
    String flightCode;
    int fuelLevel;
    String queuedAt;
    String landedAt;
    boolean emergency;

    public flight(int priority, String flightCode, int fuelLevel, boolean emergency,String queuedAt, String landedAt) {
        this.priority   = priority;
        this.flightCode = flightCode;
        this.fuelLevel  = fuelLevel;
        this.queuedAt   = queuedAt;
        this.landedAt   = landedAt;
        this.emergency = emergency;
    }

    static int calculatePriority(boolean emergency, int fuelLevel, boolean longHaul) {
        if (emergency && fuelLevel < 10) return 1;
        if (emergency)                   return 2;
        if (fuelLevel < 10)              return 3;
        if (fuelLevel < 30)              return 4;
        if (longHaul)                    return 5;
                                         return 6;
    }

    @Override
    public int compareTo(flight other) {
        if (this.priority != other.priority)
            return this.priority - other.priority;
        return this.fuelLevel - other.fuelLevel;
    }
}