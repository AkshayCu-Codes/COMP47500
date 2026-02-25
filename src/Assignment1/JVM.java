package Assignment1;

import java.util.ArrayList;

public class JVM {

	private Stack<Integer> stack;

	public JVM() {
		this.stack = new Stack<>();
	}

	// converts infix expression to JVM stack instructions
	public String[] compile(String expression) {
		String[] tokens = expression.trim().split("\\s+");
		ArrayList<String> instructions = new ArrayList<>();
		Stack<String> ops = new Stack<>(); 
		String prev = null;

		for (String token : tokens) {
			if (isNumber(token)) {
				if (prev != null && isNumber(prev)) { 
					throw new IllegalArgumentException("Missing operator between numbers");
				}
				instructions.add("PUSH " + token);
			} else if (isOperator(token)) {
				if (prev == null || isOperator(prev)) { 
					throw new IllegalArgumentException("Invalid expression");
				}
				// pop operators with higher or equal precedence first
				while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token)) {
					instructions.add(toInstruction(ops.pop()));
				}
				ops.push(token);
			} else {
				throw new IllegalArgumentException("Invalid token: " + token);
			}
			prev = token;
		}

		if (prev == null || isOperator(prev)) { 
			throw new IllegalArgumentException("Invalid expression");
		}

		while (!ops.isEmpty()) {
			instructions.add(toInstruction(ops.pop())); 
		}

		instructions.add("PRINT");
		return instructions.toArray(new String[0]);
	}

	// executes the compiled instructions on the stack
	public void execute(String[] instructions) {
		stack = new Stack<>();

		for (String instruction : instructions) {
			String[] parts = instruction.trim().split("\\s+");
			String op = parts[0].toUpperCase();

			switch (op) {
				case "PUSH":
					stack.push(Integer.parseInt(parts[1]));
					System.out.print("PUSH " + parts[1] + " -> "); stack.display();
					break;

				case "ADD": {
					int b = stack.pop(), a = stack.pop();
					stack.push(a + b);
					System.out.print("ADD -> "); stack.display();
					break;
				}
				case "SUB": {
					int b = stack.pop(), a = stack.pop();
					stack.push(a - b);
					System.out.print("SUB -> "); stack.display();
					break;
				}
				case "MUL": {
					int b = stack.pop(), a = stack.pop();
					stack.push(a * b);
					System.out.print("MUL -> "); stack.display();
					break;
				}
				case "DIV": {
					int b = stack.pop(), a = stack.pop();
					if (b == 0) throw new ArithmeticException("Division by zero");
					stack.push(a / b);
					System.out.print("DIV -> "); stack.display();
					break;
				}
				case "MOD": {
					int b = stack.pop(), a = stack.pop();
					if (b == 0) throw new ArithmeticException("Division by zero");
					stack.push(a % b);
					System.out.print("MOD -> "); stack.display();
					break;
				}
				case "PRINT":
					System.out.println("Output: " + stack.pop());
					break;

				default:
					System.out.println("Unknown instruction: " + op);
			}
		}
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public int size() {
		return stack.size();
	}

	public int peek() {
		return stack.peek();
	}

	private String toInstruction(String op) {
		switch (op) {
			case "+": return "ADD";
			case "-": return "SUB";
			case "*": return "MUL";
			case "/": return "DIV";
			case "%": return "MOD";
			default:  throw new IllegalArgumentException("Unknown operator: " + op);
		}
	}

	private int precedence(String op) {
		if (op.equals("+") || op.equals("-")) return 1;
		if (op.equals("*") || op.equals("/") || op.equals("%")) return 2;
		return 0;
	}

	private boolean isNumber(String token) {
		try { Integer.parseInt(token); return true; }
		catch (NumberFormatException e) { return false; }
	}

	private boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*")
			|| token.equals("/") || token.equals("%");
	}
}