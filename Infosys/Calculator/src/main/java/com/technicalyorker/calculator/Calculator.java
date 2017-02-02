package com.technicalyorker.calculator;

public class Calculator {
	private static final CalculatorEngine engine = new CalculatorEngine();

	public static void main(String[] args) {
		if (args.length < 1 || (args.length >= 1 && "--help".equals(args[0]))) {
			System.out.println(
					"Welcome to Technical Yorker Calculator App!!\n Operations Supported in this release: +,-,/,*,^,Sine,Tan\n\n Usage: Calculator <expression to Calculate> \n"
							+ " e.g. Calculator \"3 * ( 4 + 5 )\" ");
		} else {
			System.out.printf("Input: %s \nOutput: %s ", args[0], engine.calculate(args[0]));
		}
	}
}
