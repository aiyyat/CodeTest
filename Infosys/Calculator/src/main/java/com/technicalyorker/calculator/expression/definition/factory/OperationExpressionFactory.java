package com.technicalyorker.calculator.expression.definition.factory;

import com.technicalyorker.calculator.exception.IllegalCommandOperationException;
import com.technicalyorker.calculator.expression.DivideExpression;
import com.technicalyorker.calculator.expression.ExponentExpression;
import com.technicalyorker.calculator.expression.MinusExpression;
import com.technicalyorker.calculator.expression.MultiplyExpression;
import com.technicalyorker.calculator.expression.OperationExpression;
import com.technicalyorker.calculator.expression.PlusExpression;
import com.technicalyorker.calculator.expression.SineExpression;
import com.technicalyorker.calculator.expression.TanExpression;

/**
 * Factory for creating operations object.
 * 
 * @author achuth
 *
 */
public class OperationExpressionFactory {
	private OperationExpressionFactory() {
	}

	public static OperationExpression getOperationExpression(String s) {
		if (s.equals("+")) {
			return new PlusExpression();
		} else if (s.equals("-")) {
			return new MinusExpression();
		} else if (s.equals("*")) {
			return new MultiplyExpression();
		} else if (s.equals("/")) {
			return new DivideExpression();
		} else if (s.equals("^")) {
			return new ExponentExpression();
		} else if (s.equals("Sine")) {
			return new SineExpression();
		} else if (s.equals("Tan")) {
			return new TanExpression();
		} else {
			throw new IllegalCommandOperationException("Unknown command: " + s);
		}
	}
}
