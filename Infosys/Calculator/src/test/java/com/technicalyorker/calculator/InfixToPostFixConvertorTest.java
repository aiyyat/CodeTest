package com.technicalyorker.calculator;

import org.junit.Test;

import com.technicalyorker.calculator.exception.InvalidInputOperandCalculatorException;

import junit.framework.TestCase;

public class InfixToPostFixConvertorTest {

	@Test
	public void simple() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 4 + ", convertor.convert("3 + 4"));
	}

	@Test
	public void testBitComplexCase() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 4 5 + + ", convertor.convert("3 + ( 4 + 5 )"));
	}

	@Test
	public void testBODMAS() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 4 5 * + ", convertor.convert("3 + 4 * 5"));
	}

	@Test
	public void testAnotherBitComplexCase() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 4 5 * - ", convertor.convert("3 - 4 * 5"));
	}

	@Test
	public void testYetAnotherBitComplexCase() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 4 / 5 * ", convertor.convert("3 / 4 * 5"));
	}

	@Test
	public void testEvenAnotherBitComplexCase() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 4 5 / + ", convertor.convert("3 + 4 / 5"));
	}

	@Test
	public void testSine() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("30 Sine ", convertor.convert("Sine 30"));
	}

	@Test
	public void testSineWithProduct() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 30 Sine * ", convertor.convert("3 * Sine 30"));
	}

	@Test
	public void exponents() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("2 4 ^ ", convertor.convert("2 ^ 4"));
	}

	@Test
	public void testTan() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		TestCase.assertEquals("3 45 Tan * ", convertor.convert("3 * Tan 45"));
	}

	@Test(expected = InvalidInputOperandCalculatorException.class)
	public void testNegative() {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		convertor.convert("3 * Non-Existant-Operation 45");
	}

	public void print(String str) {
		InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
		System.out.printf(
				"@Test\npublic void simple() {\nInfixToPostFixConvertor convertor = new InfixToPostFixConvertor();\nTestCase.assertEquals(\"%s\", convertor.perform(\"%s\"));\n}",
				convertor.convert(str), str);
	}
}