package Assignment2;

import java.util.List;

public class StoreInventory {

	// initialize an instance of the bst
	private final BST<Double, Item> bst = new BST<>();
	
	
	// insertion of items
	public void addItem(String name, double price, String category) {
        bst.insert(price, new Item(name, price, category));
    }
	
	// deletion
	public void removeItem(double price) {
		bst.delete(price);
	}
	
	// search for a single item
	public Item findItem(double price) {
		return bst.search(price);
	}

	// range search, utilizing our main algorithm
	public List<Item> findInRange(double lo, double hi){
		return bst.rangeSearch(lo, hi);
	}
	
	// returns all items sorted by price ascending
	public List<Item> listAll() {
		return bst.inOrder();
	}
	
	// returns the cheapest item from the inventory
	public Item cheapest() {
		return bst.min();
	}
	
	// returns the most expensive item 
	public Item mostExpensive() {
		return bst.max();
	}
	
	//returns total number of items in store
	public int size() {
		return bst.size();
	}
	
	//check if inventory is empty
	public boolean isEmtpy() {
		return bst.isEmpty();
	}

	// prints the tree structure
	public void printTree() {
		bst.printTree();
	}

}
