package com.technicalyorker.calculator.expression;

/**
 * An Expression that is a representation of a Number
 * 
 * @author achuth
 *
 */
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
