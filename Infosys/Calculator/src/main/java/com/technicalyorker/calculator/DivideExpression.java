package com.technicalyorker.calculator;

public class DivideExpression extends AbstractDualOperationExpression {
	@Override
	public Double evaluate() {
		return getInputs()[1].evaluate() / getInputs()[0].evaluate();
	}
}
