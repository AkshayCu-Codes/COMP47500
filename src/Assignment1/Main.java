package Assignment1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		JVM jvm = new JVM();
		Scanner scanner = new Scanner(System.in);

		System.out.println("JVM Lite - Stack Based Evaluator");
		System.out.println("---------------------------------");
		System.out.println("Operators : + - * / %");
		System.out.println("Format    : 10 + 5 * 3");
		System.out.println("Type 'exit' to quit");
		System.out.println("---------------------------------\n");

		while (true) {
			System.out.print(">> ");
			String input = scanner.nextLine().trim();

			if (input.isEmpty()) continue;

			if (input.equalsIgnoreCase("exit")) {
				System.out.println("Goodbye!");
				scanner.close();
				return;
			}

			try {
				String[] instructions = jvm.compile(input);

				System.out.println("\nCompiled Instructions:");
				for (String instr : instructions) {
					System.out.println("  " + instr);
				}

				System.out.println("\nExecuting...");
				jvm.execute(instructions);
				System.out.println();

			} catch (ArithmeticException e) {
				System.out.println("Error: " + e.getMessage() + "\n");
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + " (use spaces between tokens)\n");
			}
		}
	}
}