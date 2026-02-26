package Assignment1;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        JVM jvm = new JVM();
        int[] scales = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
        Random rand = new Random();
        
        System.out.println(String.format("%-10s | %-15s | %-15s", "Scale (N)", "Total Time (ms)", "Avg Time/Run (ns)"));
        System.out.println("------------------------------------------------------------");

        for (int totalRuns : scales) {
            // Warm-up to trigger JIT compilation before measurement
            warmup(jvm);

            long startTime = System.nanoTime();
            
            for (int i = 0; i < totalRuns; i++) {
                // Generate a random expression for every single run
                // Length varies between 3 and 15 tokens to simulate different complexities
                String expression = generateRandomExpression(rand.nextInt(12) + 3);
                
                try {
                    String[] instructions = jvm.compile(expression);
                    executeSilent(jvm, instructions);
                } catch (Exception e) {
                    // Catching potential math errors
                }
            }
            
            long endTime = System.nanoTime();
            long totalDurationNs = endTime - startTime;
            double totalDurationMs = totalDurationNs / 1_000_000.0;
            long avgTimePerRunNs = totalDurationNs / totalRuns;

            System.out.println(String.format("%-10d | %-15.2f | %-15d", 
                               totalRuns, totalDurationMs, avgTimePerRunNs));
        }
    }

    private static String generateRandomExpression(int numValues) {
        String[] ops = {"+", "-", "*", "/", "%"};
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        
        for (int i = 0; i < numValues; i++) {
            sb.append(rand.nextInt(100) + 1);
            if (i < numValues - 1) {
                sb.append(" ").append(ops[rand.nextInt(ops.length)]).append(" ");
            }
        }
        return sb.toString();
    }

    //warming up JIT
    private static void warmup(JVM jvm) {
        for (int i = 0; i < 500; i++) {
            jvm.compile("1 + 1 * 2");
        }
    }

    //printing the output makes it very slow, we can get the actual time taken using this.
    private static void executeSilent(JVM jvm, String[] instructions) {
        Stack<Integer> internalStack = new Stack<>();
        for (String instruction : instructions) {
            String[] parts = instruction.trim().split("\\s+");
            String op = parts[0];
            switch (op) {
                case "PUSH": 
                    if(parts.length > 1) internalStack.push(Integer.parseInt(parts[1])); 
                    break;
                case "ADD": { int b = internalStack.pop(), a = internalStack.pop(); internalStack.push(a + b); break; }
                case "SUB": { int b = internalStack.pop(), a = internalStack.pop(); internalStack.push(a - b); break; }
                case "MUL": { int b = internalStack.pop(), a = internalStack.pop(); internalStack.push(a * b); break; }
                case "DIV": { 
                    int b = internalStack.pop(), a = internalStack.pop(); 
                    internalStack.push(b == 0 ? 0 : a / b); 
                    break; 
                }
                case "PRINT": if (!internalStack.isEmpty()) internalStack.pop(); break;
            }
        }
    }
}