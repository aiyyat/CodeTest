package com.technicalyorker.calculator.expression;
/**
 * 
 * @author achuth
 *
 */
public class DivideExpression extends AbstractDualOperandOperationExpression {
	@Override
	public Double evaluate() {
		return getInputOperands()[1].evaluate() / getInputOperands()[0].evaluate();
	}
}
