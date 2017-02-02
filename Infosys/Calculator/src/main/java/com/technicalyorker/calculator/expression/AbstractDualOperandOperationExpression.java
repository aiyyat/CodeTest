package com.technicalyorker.calculator.expression;

public abstract class AbstractDualOperandOperationExpression extends AbstractOperandOperationExpression {
	@Override
	public int getInputOperandCount() {
		return 2;
	}
}