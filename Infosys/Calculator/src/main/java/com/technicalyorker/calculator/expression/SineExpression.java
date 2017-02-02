package com.technicalyorker.calculator.expression;

public class SineExpression extends AbstractSingleOperationExpression {

	@Override
	public Double evaluate() {
		return Math.sin(getInputs()[0].evaluate());
	}

}
