package com.technicalyorker.calculator;

public abstract class AbstractSingleOperationExpression extends AbstractOperationExpression {
	@Override
	public int getInputCount() {
		return 1;
	}
}
