package com.technicalyorker.calculator.expression;

public class MultiplyExpression extends AbstractDualOperandOperationExpression {
	@Override
	public Double evaluate() {
		return getInputOperands()[0].evaluate() * getInputOperands()[1].evaluate();
	}
}
