package com.technicalyorker.calculator.expression;

public class ExponentExpression extends AbstractDualOperationExpression {
	@Override
	public Double evaluate() {
		return Math.pow(getInputs()[1].evaluate(), getInputs()[0].evaluate());
	}
}
