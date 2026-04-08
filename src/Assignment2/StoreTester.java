package Assignment2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StoreTester {
	
	private static final int[] SIZES = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
	private static final String[] CATEGORIES  = {"Electronics", "Clothing", "Food", "Sports", "Books"};
	private static final Random RNG = new Random(42);

	// random item name generator
	private static String randomName() {
        int len = 3 + RNG.nextInt(4);           
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append((char) ('a' + RNG.nextInt(26)));
        return sb.toString();
    }

	// random price generator for range search
	private static double randomPrice() {
        return Math.round((0.01 + RNG.nextDouble() * 999.98) * 100000.0) / 100000.0;
    }
	
	// random catrgory from list
	private static String randomCategory() {
        return CATEGORIES[RNG.nextInt(CATEGORIES.length)];
    }

	
	// now we generate n items with unique prices
	// we ensure unique prices exist so that we can have a new node and not overwrite values
	private static Item[] generateItems(int n) {
        Item[] items  = new Item[n];
        Set<Double> used  = new HashSet<>(n * 2);
        for (int i = 0; i < n; i++) {
            double price;
            do { 
            	price = randomPrice(); 
            } while (!used.add(price));
            items[i] = new Item(randomName(), price, randomCategory());
        }
        return items;
    }
	
	
	@FunctionalInterface
    interface Bench { 
		void run(); 
		}
	// warming up JIT
	private static long measure(int size, Bench b) {
        int warmup = size >= 100_000 ? 0 : 2;
        int runs   = size >= 100_000 ? 1 : 3;
        for (int i = 0; i < warmup; i++) b.run();
        long total = 0;
        for (int i = 0; i < runs; i++) {
            long t = System.nanoTime();
            b.run();
            total += System.nanoTime() - t;
        }
        return total / Math.max(runs, 1);
    }
    
    public static void main(String[] args) {

        System.out.println("+==========================================================+");
        System.out.println("|         BST  vs  ArrayList   —  Benchmark                |");
        System.out.println("|        Range search window: € 250 -  € 750               |");
        System.out.println("|  Each timing = average of 5 runs  (after 3 warm-up runs) |");
        System.out.println("+==========================================================+");

        // Column headers
        String hr = "─".repeat(90);
        System.out.printf("%n%-12s  %-14s %-14s   %-14s %-14s%n",
                "Size",
                "BST Insert", "AL Insert",
                "BST Range",  "AL Range");
        System.out.println(hr);
        
        // insert benchmarks
        for (int size : SIZES) {
        	RNG.setSeed(42);
            Item[] items = generateItems(size);
            
            
            long bstInsert = measure(size, () -> {
                StoreInventory store = new StoreInventory();
                for (Item it : items) store.addItem(it.name, it.price, it.category);
            });

            long alInsert = measure(size, () -> {
                List<Item> list = new ArrayList<>();
                // unsorted append
                for (Item it : items) 
                	list.add(it);          
            });
            
            // building the structures for our range search
            StoreInventory builtStore = new StoreInventory();
            List<Item> builtAL = new ArrayList<>(size);

            for (Item it : items) {
                builtStore.addItem(it.name, it.price, it.category);
                builtAL.add(it);            
                }
            
            // measuring time for range search
            long bstRange = measure(size, () -> builtStore.findInRange(250, 750));
            
            long alRange = measure(size, () -> {
                List<Item> result = new ArrayList<>();
                for (Item it : builtAL)
                    if (it.price >= 250 && it.price <= 750) 
                    	result.add(it);
            });
            
            System.out.printf("%-12s  %-14s %-14s   %-14s %-14s%n",
                formatSize(size),
                fmt(bstInsert), fmt(alInsert),
                fmt(bstRange),  fmt(alRange));
        }
        System.out.println(hr);
    }	
    
    // helper functions for formating the output
    private static String fmt(long ns) {
        if (ns < 1000)            
        	return ns + " ns";
        if (ns < 1000000)        
        	return String.format("%.2f µs", ns / 1_000.0);
        if (ns < 1000000000L)   
        	return String.format("%.2f ms", ns / 1_000_000.0);
        return String.format("%.3f s",  ns / 1_000_000_000.0);
    }
    
    private static String formatSize(int n) {
        if (n >= 1000000) return (n / 1000000) + "M";
        if (n >= 1000)     return (n / 1000) + "K";
        return String.valueOf(n);
    }
    
}