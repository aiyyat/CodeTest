package com.technicalyorker.calculator;

import static com.technicalyorker.calculator.expression.definition.PreferenceTableDefinition.getPreferenceDefinition;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class InfixToPostFixConvertor {
	Deque<String> stack = new LinkedList<>();
	StringBuffer output = new StringBuffer("");

	/**
	 * Higher the value more is preference
	 */

	private boolean higerPref(String ch1, String ch2) {
		return getPreferenceDefinition().containsKey(ch2)
				&& (getPreferenceDefinition().get(ch2).compareTo(getPreferenceDefinition().get(ch1)) >= 0);
	}

	public String perform(String infix) {
		StringBuilder output = new StringBuilder();
		Stack<String> stack = new Stack<>();
		for (String token : infix.split(" ")) {
			if (getPreferenceDefinition().containsKey(token)) {
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
		System.out.println(new InfixToPostFixConvertor().perform("Tan 45"));
		// System.out.println(new InfixToPostFixConvertor().perform("5 + 7 /
		// 2"));
	}
}
