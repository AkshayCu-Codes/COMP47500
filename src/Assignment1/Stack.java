package Assignment1;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack<T> {

	private ArrayList<T> stack;

	// initialising an empty stack
	public Stack() {
		this.stack = new ArrayList<>(); 
	}

	// adding to the top of the stack
	public void push(T value) { 
		stack.add(value);	
	}

	// removing from the top of the stack
	public T pop() { 
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	// returns top value without removing it
	public T peek() { 
		if (isEmpty()) {
			throw new EmptyStackException(); 
		}
		return stack.get(stack.size() - 1);
	}

	public boolean isEmpty() { 
		return stack.isEmpty();
	}

	public int size() { 
		return stack.size();
	}

	// prints stack contents from bottom to top
	public void display() { 
		System.out.print("[");
		for (int i = 0; i < stack.size(); i++) {
			System.out.print(stack.get(i));
			if (i != stack.size() - 1) System.out.print(", ");
		}
		System.out.println("]");
	}
}
