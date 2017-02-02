package com.technicalyorker.calculator.expression;

public abstract class AbstractSingleOperationExpression extends AbstractOperationExpression {
	@Override
	public int getInputCount() {
		return 1;
	}
}
