package com.technicalyorker.calculator.expression;

public abstract class AbstractSingleOperandOperationExpression extends AbstractOperandOperationExpression {
	@Override
	public int getInputOperandCount() {
		return 1;
	}
}
