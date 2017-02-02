package com.technicalyorker.calculator.expression;

public abstract class AbstractOperationExpression implements OperationExpression {
	Expression[] e;

	@Override
	public void setInputs(Expression... expressions) {
		if (expressions.length != getInputCount()) {
			throw new IllegalStateException("Incorrect arguments!");
		}
		e = expressions;
	}

	@Override
	public Expression[] getInputs() {
		return e;
	}
}
