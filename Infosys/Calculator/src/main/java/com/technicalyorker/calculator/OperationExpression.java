package com.technicalyorker.calculator;

public interface OperationExpression extends Expression {
	public int getInputCount();

	public void setInputs(Expression... expressions);

	public Expression[] getInputs();
}
