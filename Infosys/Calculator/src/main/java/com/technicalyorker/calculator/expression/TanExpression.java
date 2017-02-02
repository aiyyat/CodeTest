package com.technicalyorker.calculator.expression;

/**
 * 
 * @author achuth
 *
 */
public class TanExpression extends AbstractSingleOperandOperationExpression {

	@Override
	public Double evaluate() {
		return Math.tan(getInputOperands()[0].evaluate());
	}

}
