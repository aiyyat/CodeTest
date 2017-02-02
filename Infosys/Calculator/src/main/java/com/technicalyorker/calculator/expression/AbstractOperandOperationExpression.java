package com.technicalyorker.calculator.expression;

public abstract class AbstractOperandOperationExpression implements OperationExpression {
	Expression[] e;

	@Override
	public void setInputOperands(Expression... expressions) {
		if (expressions.length != getInputOperandCount()) {
			throw new IllegalStateException("Incorrect arguments!");
		}
		e = expressions;
	}

	@Override
	public Expression[] getInputOperands() {
		return e;
	}
}
