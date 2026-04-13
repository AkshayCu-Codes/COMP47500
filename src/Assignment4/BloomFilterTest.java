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

    private static List<String> buildQuerySet(List<String> urls, int urlCount) {
        Random rng = new Random(99);
        List<String> queries = new ArrayList<>(QUERY_COUNT);

        // 500 items that were definitely added
        for (int i = 0; i < QUERY_COUNT / 2; i++) {
            queries.add(urls.get(rng.nextInt(Math.min(urlCount, urls.size()))));
        }

        // 500 fresh URLs that were never added
        for (int i = 0; i < QUERY_COUNT / 2; i++) {
            queries.add("https://nonmember-" + UUID.randomUUID() + ".test/x");
        }

        return queries;
    }

    private static void runBenchmark(int count, List<String> urls, List<String> queries) {
        BloomFilter bloom = new BloomFilter(BLOOM_SIZE, NUM_HASHES);

        long t0 = System.nanoTime();
        for (String url : urls) {
            bloom.add(url);
        }
        long bloomInsertNs = System.nanoTime() - t0;

        int bloomFP = 0, bloomTP = 0, bloomTN = 0, bloomFN = 0;
        t0 = System.nanoTime();
        for (int i = 0; i < queries.size(); i++) {
            boolean result = bloom.mightContain(queries.get(i));
            boolean isMember = i < QUERY_COUNT / 2;

            if (isMember && result) bloomTP++;
            else if (isMember && !result) bloomFN++;
            else if (!isMember && result) bloomFP++;
            else bloomTN++;
        }
        long bloomLookupNs = System.nanoTime() - t0;

        t0 = System.nanoTime();
        List<String> list = new ArrayList<>(count);
        for (String url : urls) {
            list.add(url);
        }
        long listInsertNs = System.nanoTime() - t0;

        int listFP = 0, listTP = 0, listTN = 0, listFN = 0;
        t0 = System.nanoTime();
        for (int i = 0; i < queries.size(); i++) {
            boolean result = list.contains(queries.get(i));
            boolean isMember = i < QUERY_COUNT / 2;

            if (isMember && result) listTP++;
            else if (isMember && !result) listFN++;
            else if (!isMember && result) listFP++;
            else listTN++;
        }
        long listLookupNs = System.nanoTime() - t0;

        System.out.println("URLs: " + count);

        System.out.println("Bloom insert time (ms): " + (bloomInsertNs / 1e6));
        System.out.println("Bloom lookup time (ms): " + (bloomLookupNs / 1e6));
        System.out.println("Bloom TP: " + bloomTP + ", TN: " + bloomTN + ", FP: " + bloomFP + ", FN: " + bloomFN);

        System.out.println("List insert time (ms): " + (listInsertNs / 1e6));
        System.out.println("List lookup time (ms): " + (listLookupNs / 1e6));
        System.out.println("List TP: " + listTP + ", TN: " + listTN + ", FP: " + listFP + ", FN: " + listFN);

        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        for (int count : URL_COUNTS) {
            List<String> urls = generateUrls(count);
            List<String> queries = buildQuerySet(urls, count);
            runBenchmark(count, urls, queries);
        }
    }
}