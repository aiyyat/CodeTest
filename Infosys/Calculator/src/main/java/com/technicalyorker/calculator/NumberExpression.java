package com.technicalyorker.calculator;

public class NumberExpression implements Expression {
	private Double val;

	public NumberExpression(String str) {
		this.val = Double.valueOf(str);
	}

	public NumberExpression(Double i) {
		this.val = i;
	}

	@Override
	public Double evaluate() {
		return val;
	}

	@Override
	public String toString() {
		return "" + val;
	}
}
