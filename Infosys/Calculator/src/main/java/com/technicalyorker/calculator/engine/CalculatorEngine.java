package com.technicalyorker.calculator.engine;

import com.technicalyorker.calculator.algorithm.InfixToPostFixConvertor;
import com.technicalyorker.calculator.algorithm.PostFixEvaluator;

/**
 * 
 * @author achuth
 *
 */
public class CalculatorEngine {
	private static final InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
	private static final PostFixEvaluator evaluator = new PostFixEvaluator();

	public Double calculate(String str) {
		return evaluator.evaluate(convertor.convert(str)).evaluate();
	}
}
