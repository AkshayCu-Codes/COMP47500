package Assignment2;
import java.util.List;
import java.util.ArrayList;

class BST<K extends Comparable<K>, V> {
	 
    //node definition
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
 
        Node(K key, V value) {
            this.key   = key;
            this.value = value;
        }
    }
 
    private Node<K, V> root;
    private int size;
 
    //insert using recursion
	// TC: O(log n)
    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }
    
    private Node<K, V> insertRec(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) 
        	node.left  = insertRec(node.left,  key, value);
        else if (cmp > 0) 
        	node.right = insertRec(node.right, key, value);
        else              
        	node.value = value;   // update existing key
        return node;
    }

    
    //search for a specific key
	// TC: O(log n)
    public V search(K key) {
        Node<K, V> node = searchRec(root, key);
        return (node == null) ? null : node.value;
    }
 
    private Node<K, V> searchRec(Node<K, V> node, K key) {
        if (node == null)           
        	return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) 
        	return searchRec(node.left,  key);
        else if (cmp > 0) 
        	return searchRec(node.right, key);
        else              
        	return node;
    }
    
    
    //delete using recursion
	// TC: O(log n)
    public void delete(K key) {
        root = deleteRec(root, key);
    }
 
    private Node<K, V> deleteRec(Node<K, V> node, K key) {
    	
        if (node == null) 
        	return null;
        
        int cmp = key.compareTo(node.key);
        
        if (cmp < 0) {
            node.left  = deleteRec(node.left,  key);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, key);
        } else {
            if (node.left  == null) { 
            	size--; 
            	return node.right; 
            }
            if (node.right == null) { 
            	size--; 
            	return node.left;  
            }
            
            Node<K, V> successor = findMin(node.right);
            node.key   = successor.key;
            node.value = successor.value;
            node.right = deleteRec(node.right, successor.key);
        }
        return node;
    }
 
    
    // this is our main algorithm, we will be searching for items within a range
    // this is one of the main advantages of a BST
	// TC: O(log n + k), k = number of results
    public List<V> rangeSearch(K lo, K hi) {
        List<V> results = new ArrayList<>();
        rangeRec(root, lo, hi, results);
        return results;
    }

    private void rangeRec(Node<K, V> node, K lo, K hi, List<V> results) {
        if (node == null) return;

        int cmpLo = lo.compareTo(node.key);
        int cmpHi = hi.compareTo(node.key);

        // look left
        if (cmpLo < 0) 
            rangeRec(node.left, lo, hi, results); 

        // within our range
        if (cmpLo <= 0 && cmpHi >= 0) 
            results.add(node.value);               

        // look right
        if (cmpHi > 0) 
            rangeRec(node.right, lo, hi, results);
    } 
    
    public int size() { return size; }
    
    public boolean isEmpty() { return size == 0; }
    
    // printing the tree
    public void printTree() {
        printRec(root, "", true);
    }

    private void printRec(Node<K, V> node, String indent, boolean isRight) {
        if (node == null) return;
        printRec(node.right, indent + (isRight ? "        " : " |      "), true);
        System.out.println(indent + (isRight ? " /" : " \\") + "----- [" + node.key + " | " + node.value + "]");
        printRec(node.left,  indent + (isRight ? " |      " : "        "), false);
    }
    
    // returns all values via in-order traversal
    // TC: O(n)
    public List<V> inOrder() {
    	List<V> result = new ArrayList<>();
    	inOrderRec(root, result);
    	return result;
    }
    
    public void inOrderRec(Node<K, V> node, List<V> result) {
    	if (node == null)
    		return;
    	inOrderRec(node.left, result);
    	result.add(node.value);
    	inOrderRec(node.right, result);
    }
    
    // returns value with smallest key
    public V min() {
        if (root == null) 
        	return null;
        return findMin(root).value;
    }
    
    // since the left node of a bst is always the min, we return that
    private Node<K, V> findMin(Node<K, V> node) {
        while (node.left != null) 
        	node = node.left;
        return node;
    }   
    
    //returns the value with the largest key
    // TC: O(log n)
    public V max() {
        if (root == null) 
        	return null;
        
        Node<K, V> node = root;
        
        while (node.right != null) 
        	node = node.right;
        
        return node.value;
    }
}
