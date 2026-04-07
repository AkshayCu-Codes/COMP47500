package Assignment2;

import java.util.List;

public class StoreRunner {
	public static void main(String[] args) {
		
		StoreInventory store = new StoreInventory();
		
		// we first add items to the store
		store.addItem("Running Shoes", 49.99, "Sports");
		store.addItem("Python Cookbook", 12.50,  "Books");
        store.addItem("Wireless Headphones", 299.00, "Electronics");
        store.addItem("Olive Oil 1L", 8.99,  "Food");
        store.addItem("Yoga Mat", 74.95,  "Sports");
        store.addItem("T-Shirt (M)", 19.99,  "Clothing");
        store.addItem("4K Monitor", 549.00,  "Electronics");
        store.addItem("Sparkling Water 6pk", 3.49,  "Food");
        store.addItem("Mechanical Keyboard", 129.99, "Electronics");
        store.addItem("Denim Jeans", 34.99,  "Clothing");
        store.addItem("Notebook A5", 9.95,  "Books");
        store.addItem("Smart Watch", 199.00, "Electronics");
        
        // printing out all items, sorted by price
        System.out.println("Items in Store:");
        printItems(store.listAll());
        
        System.out.println("=============================================================");

        
        // searching for an exact item, based on price
        System.out.println("\nSearching for item worth €129.99:");
        Item item1 = store.findItem(129.99);
        System.out.println(item1 != null ? "  Found: " + item1 : "  Not found.");
        
        // no item of this price exists in out store
        System.out.println("\nSearching for item worth €999.00:");
        Item item2 = store.findItem(999.99);
        System.out.println(item2 != null ? "  Found: " + item2 : "  Not found.");
        
        System.out.println("=============================================================");
        
        System.out.println("\nRange Search: €10-€100");
        printItems(store.findInRange(10, 100));
        
        System.out.println("\nRange Search: €100-€600 (Expensive items)");
        printItems(store.findInRange(100, 600));

        System.out.println("\nRange Search: €0-€10 (Budget items)");
        printItems(store.findInRange(0, 10));
        
        System.out.println("=============================================================");
        
        System.out.println("\nBST internal structure");
        store.printTree();	
	}	
	
	// helper function 
	private static void printItems(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("(no items found)");
        } else {
            items.forEach(i -> System.out.println("" + i));
            System.out.println(items.size() + " item(s) present");
        }
	}
}