package com.technicalyorker.calculator.algorithm;

import java.util.EmptyStackException;
import java.util.Stack;

import com.technicalyorker.calculator.exception.InvalidPostFixExpressionException;
import com.technicalyorker.calculator.expression.Expression;
import com.technicalyorker.calculator.expression.NumberExpression;
import com.technicalyorker.calculator.expression.OperationExpression;
import com.technicalyorker.calculator.expression.definition.factory.OperationExpressionFactory;
import com.technicalyorker.calculator.util.Util;

/**
 * 
 * @author achuth
 *
 */
public class PostFixEvaluator {
	private static final Stack<Expression> stack = new Stack<>();

	public Expression evaluate(String str) {
		try {
			for (String s : str.split(" ")) {
				if (Util.isNumeric(s)) {
					stack.push(new NumberExpression(s));
				} else {
					OperationExpression p = OperationExpressionFactory.getOperationExpression(s);
					Expression[] es = new Expression[p.getInputOperandCount()];
					for (int i = 0; i < p.getInputOperandCount(); i++) {
						es[i] = stack.pop();
					}
					p.setInputOperands(es);
					stack.push(new NumberExpression(p.evaluate()));
				}
			}
		} catch (EmptyStackException e) {
			throw new InvalidPostFixExpressionException("Invalid PostFixExpression: " + str);
		}
		return stack.pop();
	}
}