package com.technicalyorker.calculator;

import org.junit.Test;

import com.technicalyorker.calculator.algorithm.InfixToPostFixConvertor;
import com.technicalyorker.calculator.algorithm.PostFixEvaluator;
import com.technicalyorker.calculator.exception.InvalidPostFixExpressionException;

import junit.framework.TestCase;

/**
 * 
 * @author achuth
 *
 */
public class PostFixEvaluatorTest {

	@Test
	public void simple() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(7.0, evaluator.evaluate("3 4 + ").evaluate());
	}

	@Test
	public void slightlyMoreSimple() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(12.0, evaluator.evaluate("3 4 5 + + ").evaluate());
	}

	@Test
	public void slightlyComplex() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(23.0, evaluator.evaluate("3 4 5 * + ").evaluate());
	}

	@Test
	public void slightlyMoreComplex() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(-17.0, evaluator.evaluate("3 4 5 * - ").evaluate());
	}

	@Test
	public void anotherComplexCase() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(3.75, evaluator.evaluate("3 4 / 5 * ").evaluate());
	}

	@Test
	public void yetAnotherComplexCase() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(3.8, evaluator.evaluate("3 4 5 / + ").evaluate());
	}

	@Test
	public void sineTestCase() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(-0.9880316240928618, evaluator.evaluate("30 Sine ").evaluate());
	}

	@Test
	public void sineWithProductCase() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(-2.9640948722785856, evaluator.evaluate("3 30 Sine * ").evaluate());
	}

	@Test
	public void exponentialCase() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(16.0, evaluator.evaluate("2 4 ^ ").evaluate());
	}

	@Test
	public void tanTestCase() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		TestCase.assertEquals(4.859325571631585, evaluator.evaluate("3 45 Tan * ").evaluate());
	}

	@Test(expected = InvalidPostFixExpressionException.class)
	public void testNegativeCae() {
		PostFixEvaluator evaluator = new PostFixEvaluator();
		evaluator.evaluate("3 45 Tan * * ");
	}

	public void print(String str) {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		PostFixEvaluator evaluator = new PostFixEvaluator();
		System.out.printf(
				"@Test\npublic void simple() {\nPostFixEvaluator evaluator = new PostFixEvaluator();\nTestCase.assertEquals(%s, evaluator.evaluate(\"%s\").evaluate());\n}",
				evaluator.evaluate(convertor.convert(str)), convertor.convert(str));
	}

}
