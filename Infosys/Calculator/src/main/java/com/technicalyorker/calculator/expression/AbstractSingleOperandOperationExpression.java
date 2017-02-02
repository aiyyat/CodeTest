package com.technicalyorker.calculator.expression;

/**
 * Defines Single Operand Operation Expressions
 * 
 * @author achuth
 *
 */
public abstract class AbstractSingleOperandOperationExpression extends AbstractOperandOperationExpression {
	@Override
	public int getInputOperandCount() {
		return 1;
	}
}
