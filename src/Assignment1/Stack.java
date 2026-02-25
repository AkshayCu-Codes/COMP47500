package Assignment1;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {

	private ArrayList<T> stack;

	public Stack() {
		this.stack = new ArrayList<>(); // initialising an empty stack
	}

	public void push(T value) { // adding to the top of the stack
		stack.add(value);
	}

	public T pop() { // removing from the top of the stack
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	public T peek() { // returns the top element without removing it
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(stack.size() - 1);
	}

	public boolean isEmpty() { // checking if the stack is empty
		return stack.isEmpty();
	}

	public int size() { // returns the number of elements in the stack
		return stack.size();
	}

	public void display() { // printing the stack contents
		System.out.print("[");
		for (int i = 0; i < stack.size(); i++) {
			System.out.print(stack.get(i));
			if (i != stack.size() - 1) System.out.print(", ");
		}
		System.out.println("]");
	}
}