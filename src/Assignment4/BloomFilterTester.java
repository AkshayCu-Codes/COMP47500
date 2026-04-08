package Assignment4;

public class BloomFilterTester {
	
	public static void main(String[] args) {
		BloomFilter bf = new BloomFilter(10000, 7);
		
		bf.add("apple");
	    bf.add("banana");
	    bf.add("cherry");

	    System.out.println(bf.mightContain("apple"));   // true  (definitely added)
	    System.out.println(bf.mightContain("grape"));   // false (definitely not added)
	    System.out.println(bf.mightContain("banana"));  // true
	    System.out.println(bf.mightContain("mango"));   // false (most likely)
	}
}
