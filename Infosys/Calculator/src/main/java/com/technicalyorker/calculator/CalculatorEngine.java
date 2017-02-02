package com.technicalyorker.calculator;

public class CalculatorEngine {
	private static final InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
	private static final PostFixEvaluator evaluator = new PostFixEvaluator();

	public Double calculate(String str) {
		return evaluator.evaluate(convertor.perform(str)).evaluate();
	}
}
