package Assignment2;

import java.util.*;

public class Test {

    // we generate random names for the nodes so that we can avoid duplicates
    private static String randomName(Random rng) {
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                          "Frank", "Grace", "Hank", "Ivan", "Jane",
                          "Karl", "Laura", "Mike", "Nina", "Oscar"};
        return names[rng.nextInt(names.length)] + "_" + rng.nextInt(99999);
    }

    
    private static void runBenchmark(int nodeCount) {
        BST<Integer, String> bst = new BST<>();
        
        // fixed seed for reproducibility, 42 is the answer to the universe.
        Random rng = new Random(42); 

        // inserting the values into the bst
        long insertStart = System.nanoTime();
        for (int i = 0; i < nodeCount; i++) {
            int key = rng.nextInt(nodeCount * 10); 
            bst.insert(key, randomName(rng));
        }
        long insertEnd = System.nanoTime();

        // pick 5 random ranges and measure average time taken for it
        int numSearches = 5;
        long totalSearchTime = 0;
        List<Integer> resultSizes = new ArrayList<>();

        for (int s = 0; s < numSearches; s++) {
            int lo = rng.nextInt(nodeCount * 10);
            int hi = lo + (nodeCount / 10); // our range window is 10% of node count

            long searchStart = System.nanoTime();
            List<String> results = bst.rangeSearch(lo, hi);
            long searchEnd = System.nanoTime();

            totalSearchTime += (searchEnd - searchStart);
            resultSizes.add(results.size());
        }

        long avgSearchNs = totalSearchTime / numSearches;

        // --- Report ---
        System.out.printf("%-12s | BST size: %-8d | Insert: %-10s | Avg RangeSearch: %-10s | Avg hits: %-6s%n",
            nodeCount,
            bst.size(),
            formatTime(insertEnd - insertStart),
            formatTime(avgSearchNs),
            resultSizes.stream().mapToInt(i -> i).sum() / numSearches
        );
    }

    // time formating
    private static String formatTime(long nanos) {
        if (nanos < 1000000)
            return nanos + " ns";
        else if (nanos < 1000000000)
            return (nanos / 1000000) + " ms";
        else
            return String.format("%.2f s", nanos / 1000000000.0);
    }

    public static void main(String[] args) {
        int[] scales = {10, 100, 1000, 10000, 100000, 1000000};

        System.out.printf("%-12s | %-14s | %-16s | %-22s | %-10s%n",
            "Node Count", "BST Size          ", "Insert Time       ", "Avg RangeSearch Time       ", "Avg Hits");
        System.out.println("-".repeat(100));

        for (int scale : scales) {
            runBenchmark(scale);
        }

        System.out.println("-".repeat(100));
    }
}
