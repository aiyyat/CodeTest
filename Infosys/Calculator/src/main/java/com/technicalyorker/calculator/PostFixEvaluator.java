package com.technicalyorker.calculator;

import java.util.Stack;

import com.technicalyorker.calculator.expression.Expression;
import com.technicalyorker.calculator.expression.NumberExpression;
import com.technicalyorker.calculator.expression.OperationExpression;
import com.technicalyorker.calculator.expression.factory.OperationExpressionFactory;
import com.technicalyorker.calculator.util.Util;

/**
 * Hello world!
 *
 */
public class PostFixEvaluator {
	public static void main(String[] args) {
		new PostFixEvaluator().perform();
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
			if (Util.isNumeric(s)) {
				stack.push(new NumberExpression(s));
			} else {
				OperationExpression p = OperationExpressionFactory.getOperationExpression(s);
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
}
