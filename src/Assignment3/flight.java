package Assignment3;

public class flight implements Comparable<flight> {
    int priority;
    String flightCode;
    int fuelLevel;
    long arrivalTime;
    
    public flight(int priority, String flightCode, int fuelLevel) {
        this.priority   = priority;
        this.flightCode = flightCode;
        this.fuelLevel  = fuelLevel;
        this.arrivalTime=System.nanoTime();
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
