package com.technicalyorker.calculator;

import java.util.Deque;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class InfixToPostFixConvertor {
	Deque<String> stack = new LinkedList<>();
	StringBuffer output = new StringBuffer("");
	Map<String, Integer> preferenceTable = new Hashtable<>();

	/**
	 * Higher the value more is preference
	 */
	public InfixToPostFixConvertor() {
		preferenceTable.put("+", 1);
		preferenceTable.put("-", 1);
		preferenceTable.put("*", 2);
		preferenceTable.put("/", 2);
		preferenceTable.put("Sine", 3);
	}

	private boolean higerPref(String ch1, String ch2) {
		return preferenceTable.containsKey(ch2) && (preferenceTable.get(ch2).compareTo(preferenceTable.get(ch1)) >= 0);
	}

	public String perform(String infix) {
		StringBuilder output = new StringBuilder();
		Stack<String> stack = new Stack<>();
		for (String token : infix.split(" ")) {
			if (preferenceTable.containsKey(token)) {
				while (!stack.isEmpty() && higerPref(token, stack.peek()))
					output.append(stack.pop()).append(' ');
				stack.push(token);
			} else if (token.equals("(")) {
				stack.push(token);
			} else if (token.equals(")")) {
				while (!stack.peek().equals("("))
					output.append(stack.pop()).append(' ');
				stack.pop();
			} else {
				output.append(token).append(' ');
			}
		}

		while (!stack.isEmpty())
			output.append(stack.pop()).append(' ');

		return output.toString();
	}

	public static void main(String[] args) {
		System.out.println(new InfixToPostFixConvertor().perform("( 5 + 7 ) * 2"));
		// System.out.println(new InfixToPostFixConvertor().perform("5 + 7 /
		// 2"));
	}
}
