package Assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BloomFilterTest {

	
	static final int NUM_HASHES = 5;
    static final int BLOOM_SIZE = 1000000;
    static final int QUERY_COUNT = 1000;
    static final int[] URL_COUNTS = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
    
    static final String[] DOMAINS = {"com", "org", "net", "io", "co.uk", "dev", "ai"};
    static final String[] PREFIXES = {"https://www.", "http://www.", "https://", "http://"};

    // url generator
    private static List<String> generateUrls(int count) {
    	List<String> urls = new ArrayList<>(count);
    	Random rng = new Random(42);
    	for (int i=0;i<count;i++)
    	{
    		String prefix = PREFIXES[rng.nextInt(PREFIXES.length)];
    		String domain = DOMAINS[rng.nextInt(DOMAINS.length)];
    		String path = randomAlpha(rng, 6 + rng.nextInt(10));
    		urls.add(prefix + path + "." + domain + "/" + randomAlpha(rng, 4));
    	}
    	return urls;
    }
        
	private static String randomAlpha(Random rng, int len) {
		StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append((char)('a' + rng.nextInt(26)));
        return sb.toString();
	}
	
	
	private static List<String> buildQuerySet(List<String> urls, int urlCount) {
        Random rng = new Random(99);
        List<String> queries = new ArrayList<>(QUERY_COUNT);

        // 500 items that were definitely added
        for (int i = 0; i <QUERY_COUNT/2 ; i++)
            queries.add(urls.get(rng.nextInt(Math.min(urlCount, urls.size()))));

        // 500 fresh URLs that were never added (to measure false positives)
        for (int i = 0; i < QUERY_COUNT/2 ; i++)
            queries.add("https://nonmember-" + UUID.randomUUID() + ".test/x");

        return queries;
    }

	private static void runBenchmark(int count, List<String> urls, List<String> queries) {

        
        BloomFilter bloom = new BloomFilter(BLOOM_SIZE, NUM_HASHES);

        long t0 = System.nanoTime();
        for (String url : urls) bloom.add(url);
        long bloomInsertNs = System.nanoTime() - t0;

        int bloomFP = 0, bloomTP = 0, bloomTN = 0, bloomFN = 0;
        t0 = System.nanoTime();
        for (int i = 0; i < queries.size(); i++) {
            boolean result = bloom.mightContain(queries.get(i));
            boolean isMember = i < QUERY_COUNT / 2;
            if  (isMember  &&  result) bloomTP++;
            else if (isMember  && !result) bloomFN++;
            else if (!isMember &&  result) bloomFP++;
            else bloomTN++;
        }
        long bloomLookupNs = System.nanoTime() - t0;
        
     // array list for comparison
        List<String> list = new ArrayList<>(urls);

        t0 = System.nanoTime();
        
        List<String> listFresh = new ArrayList<>(count);
        for (String url : urls) listFresh.add(url);
        long listInsertNs = System.nanoTime() - t0;

        int listFP = 0, listTP = 0, listTN = 0, listFN = 0;
        t0 = System.nanoTime();
        for (int i = 0; i < queries.size(); i++) {
            boolean result = list.contains(queries.get(i));
            boolean isMember = i < QUERY_COUNT / 2;
            if  (isMember  &&  result) listTP++;
            else if (isMember  && !result) listFN++;
            else if (!isMember &&  result) listFP++;
            else listTN++;
        }
        long listLookupNs = System.nanoTime() - t0;
        
        // memory size calculations
        double bloomMemKB = BLOOM_SIZE / 8.0 / 1024.0;
        long avgUrlBytes  = 40; // rough avg URL length in bytes
        double listMemKB  = (count * avgUrlBytes) / 1024.0;

        // false positive rate calculations
        double fpRate = (bloomFP / (double)(QUERY_COUNT / 2)) * 100.0;

        // lookup time calculations
        double bloomLookupOps = (QUERY_COUNT / (bloomLookupNs / 1e9));
        double listLookupOps  = (QUERY_COUNT / (listLookupNs  / 1e9));

        printRow(count,
                bloomInsertNs, listInsertNs,
                bloomLookupNs, listLookupNs,
                bloomLookupOps, listLookupOps,
                fpRate,
                bloomMemKB, listMemKB);
    }
	
	public static void main(String[] args) {
		printHeader();

		for (int count: URL_COUNTS) {
			List<String> urls = generateUrls(count);
			List<String> queries = buildQuerySet(urls, count);
			
			runBenchmark(count, urls, queries);
		}	
	}
	
	static void printHeader() {
        System.out.println("+-----------+---------------------------+-----------------------------+--------------------------+----------+----------------------+");
        System.out.println("|    URLs   |     Insert Time (ms)      |      Lookup Time (ms)       |    Throughput (ops/s)    |  FP Rate |    Memory (KB)       |");
        System.out.println("|           |  Bloom      |  List       |  Bloom        |  List       |  Bloom    |  List        |  Bloom   |  Bloom  |  List      |");
        System.out.println("+-----------+---------------------------+-----------------------------+--------------------------+----------+----------------------+");
    }
	
	static void printRow(int count,
            long bloomInsNs, long listInsNs,
            long bloomLookNs, long listLookNs,
            double bloomOps, double listOps,
            double fpRate,
            double bloomMemKB, double listMemKB) {
		System.out.printf(
	            "│ %9s │ %11.3f │ %11.3f │ %13.3f │ %11.3f │ %9.0f │ %12.0f │ %7.2f%% │ %7.1f │ %10.1f │%n",
	            formatCount(count),
	            bloomInsNs  / 1e6,
	            listInsNs   / 1e6,
	            bloomLookNs / 1e6,
	            listLookNs  / 1e6,
	            bloomOps,
	            listOps,
	            fpRate,
	            bloomMemKB,
	            listMemKB
	        );

	        if (count == 10_000_000) {
	            System.out.println(
	                "+-----------+---------------------------+-----------------------------+--------------------------+----------+----------------------+");
	        }
	    }
	static String formatCount(int n) {
        if (n >= 1000000) return (n / 1000000) + "M";
        if (n >= 1000)     return (n / 1000) + "K";
        return String.valueOf(n);
    }

}