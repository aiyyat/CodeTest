package com.technicalyorker.calculator;

import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		new App().perform();
	}

	private void perform() {
		// System.out.println(evaluate("4 3 2 - 1 + *").toString());
		// System.out.println(evaluate("3 4 5 + Sine *").toString());
		System.out.println(evaluate("5 2 4 1 - ^ +").toString());
	}

	Stack<Expression> stack = new Stack<>();

	public Expression evaluate(String str) {
		String split[] = str.split(" ");
		for (String s : split) {
			if (isNumeric(s)) {
				stack.push(new NumberExpression(s));
			} else {
				OperationExpression p;
				if (s.equals("+")) {
					p = new PlusExpression();
				} else if (s.equals("-")) {
					p = new MinusExpression();
				} else if (s.equals("*")) {
					p = new MultiplyExpression();
				} else if (s.equals("/")) {
					p = new DivideExpression();
				} else if (s.equals("^")) {
					p = new ExponentExpression();
				} else if (s.equals("Sine")) {
					p = new SineExpression();
				} else {
					throw new IllegalArgumentException();
				}
				Expression[] es = new Expression[p.getInputCount()];
				for (int i = 0; i < p.getInputCount(); i++) {
					es[i] = stack.pop();
				}
				p.setInputs(es);
				stack.push(new NumberExpression(p.evaluate()));
			}
		}
		return stack.pop();
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(.\\d+)?");
	}
}
