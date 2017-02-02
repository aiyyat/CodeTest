package com.technicalyorker.calculator.expression;

public class ExponentExpression extends AbstractDualOperandOperationExpression {
	@Override
	public Double evaluate() {
		return Math.pow(getInputOperands()[1].evaluate(), getInputOperands()[0].evaluate());
	}
}
