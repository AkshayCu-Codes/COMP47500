package Assignment2;

import java.util.List;

public class StoreInventory {

	// initialize an instance of the bst
	private final BST<Double, Item> bst = new BST<>();
	
	
	// insertion
	// TC: O(log n)
	public void addItem(String name, double price, String category) {
        bst.insert(price, new Item(name, price, category));
    }
	
	// deletion
	// TC: O(log n)
	public void removeItem(double price) {
		bst.delete(price);
	}
	
	// search for a single item
	// TC: O(log n)
	public Item findItem(double price) {
		return bst.search(price);
	}

	// range search, utilizing our main algorithm
	// TC: O(log n + k), where k is the number of results.
	public List<Item> findInRange(double lo, double hi){
		return bst.rangeSearch(lo, hi);
	}

}
