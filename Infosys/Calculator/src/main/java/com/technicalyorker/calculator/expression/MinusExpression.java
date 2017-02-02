package com.technicalyorker.calculator.expression;

public class MinusExpression extends AbstractDualOperandOperationExpression {
	@Override
	public Double evaluate() {
		return getInputOperands()[1].evaluate() - getInputOperands()[0].evaluate();
	}
}
