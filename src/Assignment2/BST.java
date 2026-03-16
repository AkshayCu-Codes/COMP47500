package Assignment2;

class BST<K extends Comparable<K>, V> {
	 
    //Node (internal)
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
 
    //Insert
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

    
    //Search
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
    
    
    //Delete
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
 
    private Node<K, V> findMin(Node<K, V> node) {
        while (node.left != null) 
        	node = node.left;
        return node;
    }   
}
