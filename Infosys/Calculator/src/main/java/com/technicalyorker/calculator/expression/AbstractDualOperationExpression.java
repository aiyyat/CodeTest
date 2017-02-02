package com.technicalyorker.calculator.expression;

public abstract class AbstractDualOperationExpression extends AbstractOperationExpression {
	@Override
	public int getInputCount() {
		return 2;
	}
}