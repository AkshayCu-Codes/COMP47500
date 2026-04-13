package Assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BloomFilterTest {

    static final int NUM_HASHES = 5;
    static final int BLOOM_SIZE = 1000000;
    static final int[] URL_COUNTS = {10, 100, 1000, 10000, 100000, 1000000, 10000000};

    static final String[] DOMAINS = {"com", "org", "net", "io", "co.uk", "dev", "ai"};
    static final String[] PREFIXES = {"https://www.", "http://www.", "https://", "http://"};

    // url generator
    private static List<String> generateUrls(int count) {
        List<String> urls = new ArrayList<>(count);
        Random rng = new Random(42);
        for (int i = 0; i < count; i++) {
            String prefix = PREFIXES[rng.nextInt(PREFIXES.length)];
            String domain = DOMAINS[rng.nextInt(DOMAINS.length)];
            String path = randomAlpha(rng, 6 + rng.nextInt(10));
            urls.add(prefix + path + "." + domain + "/" + randomAlpha(rng, 4));
        }
        return urls;
    }

    private static String randomAlpha(Random rng, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append((char) ('a' + rng.nextInt(26)));
        }
        return sb.toString();
    }

    private static void runBenchmark(int count, List<String> urls) {
        BloomFilter bloom = new BloomFilter(BLOOM_SIZE, NUM_HASHES);

        long t0 = System.nanoTime();
        for (String url : urls) {
            bloom.add(url);
        }
        long insertNs = System.nanoTime() - t0;

        t0 = System.nanoTime();
        int found = 0;
        for (String url : urls) {
            if (bloom.mightContain(url)) {
                found++;
            }
        }
        long lookupNs = System.nanoTime() - t0;

        System.out.println("URLs: " + count);
        System.out.println("Bloom insert time (ms): " + (insertNs / 1e6));
        System.out.println("Bloom lookup time (ms): " + (lookupNs / 1e6));
        System.out.println("Items found: " + found + "/" + urls.size());
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        for (int count : URL_COUNTS) {
            List<String> urls = generateUrls(count);
            runBenchmark(count, urls);
        }
    }
}