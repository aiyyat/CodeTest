package com.technicalyorker.calculator;

import org.junit.Test;

import com.technicalyorker.calculator.exception.IllegalCommandOperationException;
import com.technicalyorker.calculator.expression.DivideExpression;
import com.technicalyorker.calculator.expression.ExponentExpression;
import com.technicalyorker.calculator.expression.MinusExpression;
import com.technicalyorker.calculator.expression.MultiplyExpression;
import com.technicalyorker.calculator.expression.PlusExpression;
import com.technicalyorker.calculator.expression.SineExpression;
import com.technicalyorker.calculator.expression.TanExpression;
import com.technicalyorker.calculator.expression.definition.factory.OperationExpressionFactory;

import junit.framework.TestCase;

/**
 * 
 * @author achuth
 *
 */
public class OperationExpressionFactoryTest {
	@Test
	public void minusTest() {
		TestCase.assertEquals(MinusExpression.class, OperationExpressionFactory.getOperationExpression("-").getClass());
	}

	@Test
	public void plusTest() {
		TestCase.assertEquals(PlusExpression.class, OperationExpressionFactory.getOperationExpression("+").getClass());
	}

	@Test
	public void multiplyTest() {
		TestCase.assertEquals(MultiplyExpression.class,
				OperationExpressionFactory.getOperationExpression("*").getClass());
	}

	@Test
	public void divideTest() {
		TestCase.assertEquals(DivideExpression.class,
				OperationExpressionFactory.getOperationExpression("/").getClass());
	}

	@Test
	public void exponentTest() {
		TestCase.assertEquals(ExponentExpression.class,
				OperationExpressionFactory.getOperationExpression("^").getClass());
	}

	@Test
	public void sineTest() {
		TestCase.assertEquals(SineExpression.class,
				OperationExpressionFactory.getOperationExpression("Sine").getClass());
	}

	@Test
	public void tanTest() {
		TestCase.assertEquals(TanExpression.class, OperationExpressionFactory.getOperationExpression("Tan").getClass());
	}

	@Test(expected = IllegalCommandOperationException.class)
	public void illegalCommandTest() {
		OperationExpressionFactory.getOperationExpression(":)");
	}
}