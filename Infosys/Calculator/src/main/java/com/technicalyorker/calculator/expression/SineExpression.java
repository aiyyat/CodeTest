package com.technicalyorker.calculator.expression;

/**
 * 
 * @author achuth
 *
 */
public class SineExpression extends AbstractSingleOperandOperationExpression {

	@Override
	public Double evaluate() {
		return Math.sin(getInputOperands()[0].evaluate());
	}

}
