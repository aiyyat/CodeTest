package com.technicalyorker.calculator.expression;

public interface OperationExpression extends Expression {
	public int getInputOperandCount();

	public void setInputOperands(Expression... expressions);

	public Expression[] getInputOperands();
}
