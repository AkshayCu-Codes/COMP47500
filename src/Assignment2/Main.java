package Assignment2;
import java.util.List;


public class Main {
    public static void main(String[] args) {
	
	BST<Integer, String> gradebook = new BST<>();

    System.out.println("Inserting details");
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
    
    System.out.println("-".repeat(100));

    System.out.println("\nSearch by Grade");
    int[] searchGrades = {72, 91, 60, 99};
    for (int grade : searchGrades) {
        String student = gradebook.search(grade);
        if (student != null) {
            System.out.println("Grade " + grade + ": " + student);
        } else {
            System.out.println("Grade " + grade + ": No student found");
        }
    }
    
    System.out.println("-".repeat(100));
    
    System.out.println("\nRange Search: Grades 70 - 90");
    List<String> midRange = gradebook.rangeSearch(70, 90);
    if (midRange.isEmpty()) {
        System.out.println("No students found in this range.");
    } else {
        System.out.println("Students scoring between 70 and 90: " + midRange);
    }
    
    System.out.println("-".repeat(100));
    
    System.out.println("Tree Structure");
    gradebook.printTree();
    
    }
}
