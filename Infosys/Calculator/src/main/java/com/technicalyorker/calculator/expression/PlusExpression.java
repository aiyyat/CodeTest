package com.technicalyorker.calculator.expression;

public class PlusExpression extends AbstractDualOperandOperationExpression {
	@Override
	public Double evaluate() {
		return getInputOperands()[0].evaluate() + getInputOperands()[1].evaluate();
	}
}
