package com.technicalyorker.calculator;

import static com.technicalyorker.calculator.expression.definition.PreferenceDefinition.getDefinition;
import static com.technicalyorker.calculator.expression.definition.PreferenceDefinition.higerPref;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import com.technicalyorker.calculator.exception.InvalidInputOperandCalculatorException;
import com.technicalyorker.calculator.util.Util;

public class InfixToPostFixConvertor {
	Deque<String> stack = new LinkedList<>();
	StringBuffer output = new StringBuffer("");

	/**
	 * Higher the value more is preference
	 */

	public String perform(String infix) {
		StringBuilder output = new StringBuilder();
		Stack<String> stack = new Stack<>();
		for (String token : infix.split(" ")) {
			if (getDefinition().containsKey(token)) {
				while (!stack.isEmpty() && higerPref(token, stack.peek()))
					output.append(stack.pop()).append(' ');
				stack.push(token);
			} else if (token.equals("(")) {
				stack.push(token);
			} else if (token.equals(")")) {
				while (!stack.peek().equals("("))
					output.append(stack.pop()).append(' ');
				stack.pop();
			} else if (Util.isNumeric(token)) {
				output.append(token).append(' ');
			} else {
				throw new InvalidInputOperandCalculatorException("Invalid Input token: " + token);
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
