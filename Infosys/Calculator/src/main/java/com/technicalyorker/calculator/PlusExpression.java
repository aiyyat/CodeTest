package com.technicalyorker.calculator;

public class PlusExpression extends AbstractDualOperationExpression {
	@Override
	public Double evaluate() {
		return getInputs()[0].evaluate() + getInputs()[1].evaluate();
	}
}
