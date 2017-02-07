package com.technicalyorker.calculator;

import org.junit.Test;

import com.technicalyorker.calculator.engine.CalculatorEngine;
import com.technicalyorker.calculator.exception.InvalidPostFixExpressionException;

import junit.framework.TestCase;

/**
 * 
 * @author achuth
 *
 */
public class IntegrationTest {

	@Test
	public void simple() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(7.0, evaluator.calculate("3 + 4"));
	}

	@Test
	public void slightlyMoreSimple() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(12.0, evaluator.calculate("3 + 4 + 5"));
	}

	@Test
	public void slightlyComplex() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(23.0, evaluator.calculate("3 + 4 * 5"));
	}

	@Test
	public void slightlyMoreComplex() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(-17.0, evaluator.calculate("3 - 4 * 5"));
	}

	@Test
	public void anotherComplexCase() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(3.75, evaluator.calculate("3 / 4 * 5"));
	}

	@Test
	public void yetAnotherComplexCase() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(3.8, evaluator.calculate("3 + 4 / 5"));
	}

	@Test
	public void sineTestCase() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(-0.9880316240928618, evaluator.calculate("Sine 30"));
	}

	@Test
	public void sineWithProductCase() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(-2.9640948722785856, evaluator.calculate("3 * Sine 30"));
	}

	@Test
	public void exponentialCase() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(16.0, evaluator.calculate("2 ^ 4"));
	}

	@Test
	public void tanTestCase() {
		CalculatorEngine evaluator = new CalculatorEngine();
		TestCase.assertEquals(4.859325571631585, evaluator.calculate("3 * Tan 45"));
	}

	@Test(expected = InvalidPostFixExpressionException.class)
	public void testNegativeCae() {
		CalculatorEngine evaluator = new CalculatorEngine();
		evaluator.calculate("3 * Tan 45 * ");
	}
}
