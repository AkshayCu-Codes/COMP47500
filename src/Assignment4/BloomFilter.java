package Assignment4;



public class BloomFilter {
	
	private final HashTable bitArray;
	private final int size;		   // number of bits, m
	private final int numHashes;   // number of hash functions, k
	
	public BloomFilter(int size, int numHashes){
		this.size = size;
		this.numHashes = numHashes;
		this.bitArray = new HashTable(size);
	}
	
	// hash functions
	private long hash1(String item){
		return item.hashCode();
	}
	
	private long hash2(String item){
		long hash = 2166136261L;
		for (char c: item.toCharArray()) {
			hash ^= c;
			hash *= 1099511628211L;
		}
		return hash;
	}
	
	private int getHash(String item, int i) {
		long h = (hash1(item) + (long)i * hash2(item)) % size;
		return (int) Math.abs(h);
	}
	
	// core operations
	// set k bits for the given url
	public void add(String item) {
		for (int i=0; i<numHashes;i++) {
			bitArray.set(getHash(item, i));
		}
	}
	// checker function
	// returns false if item is definitely not in the set
	// returns true if item is probably in the set. (false positive is possible)
	public boolean mightContain(String item) {
		for (int i=0; i< numHashes; i++) {
			if (!bitArray.get(getHash(item, i))) {
				return false;  // guaranteed miss
			}
		}
		return true; 
	}
	
	public void clear() {
		bitArray.clear();
	}
}
