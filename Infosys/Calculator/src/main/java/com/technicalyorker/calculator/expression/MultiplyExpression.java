package com.technicalyorker.calculator.expression;

/**
 * 
 * @author achuth
 *
 */
public class MultiplyExpression extends AbstractDualOperandOperationExpression {
	@Override
	public Double evaluate() {
		return getInputOperands()[0].evaluate() * getInputOperands()[1].evaluate();
	}
}
