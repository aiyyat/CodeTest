package com.technicalyorker.calculator;

public class ExponentExpression extends AbstractDualOperationExpression {
	@Override
	public Double evaluate() {
		return Math.pow(getInputs()[1].evaluate(), getInputs()[0].evaluate());
	}
}
