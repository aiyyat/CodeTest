package com.technicalyorker.calculator;

public abstract class AbstractDualOperationExpression extends AbstractOperationExpression {
	@Override
	public int getInputCount() {
		return 2;
	}
}