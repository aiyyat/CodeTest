package com.technicalyorker.calculator.expression;

public interface OperationExpression extends Expression {
	public int getInputCount();

	public void setInputs(Expression... expressions);

	public Expression[] getInputs();
}
