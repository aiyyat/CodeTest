package com.technicalyorker.calculator;

import java.io.InputStream;
import java.util.Scanner;

public class InterfaceEngine {
	InfixToPostFixConvertor convertor = new InfixToPostFixConvertor();
	PostFixEvaluator evaluator = new PostFixEvaluator();

	public Double calculate(InputStream is) {
		Scanner s = new Scanner(is);
		StringBuffer input = new StringBuffer("");
		while (s.next() != null) {
			input.append(s).append(' ');
		}
		return calculate(input.toString());
	}

	public Double calculate(String str) {
		return evaluator.evaluate(convertor.perform(str)).evaluate();
	}

	public static void main(String[] args) {
		System.out.println(new InterfaceEngine().calculate("( 5 + 7 ) * 2"));
		System.out.println(new InterfaceEngine().calculate("Tan 45"));
	}
}
