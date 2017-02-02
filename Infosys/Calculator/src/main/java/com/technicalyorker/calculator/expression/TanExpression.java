package com.technicalyorker.calculator.expression;

public class TanExpression extends AbstractSingleOperandOperationExpression {

	@Override
	public Double evaluate() {
		return Math.tan(getInputOperands()[0].evaluate());
	}

}
