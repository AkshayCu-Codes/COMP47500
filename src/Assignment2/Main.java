package Assignment2;
import java.util.List;


public class Main {
    public static void main(String[] args) {
	
	BST<Integer, String> gradebook = new BST<>();

    System.out.println("=== Inserting Students ===");
    gradebook.insert(85, "Alice");
    gradebook.insert(72, "Bob");
    gradebook.insert(91, "Charlie");
    gradebook.insert(60, "Diana");
    gradebook.insert(78, "Eve");
    gradebook.insert(95, "Frank");
    gradebook.insert(55, "Grace");
    gradebook.insert(88, "Hank");
    gradebook.insert(72, "Ivan");   
    gradebook.insert(100, "Jane");
    
    System.out.println("Students inserted. Total size: " + gradebook.size());

    System.out.println("\n=== BST Structure ===");
    gradebook.printTree();

    
    }
}
