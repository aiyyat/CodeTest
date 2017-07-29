package com.technicalyorker.payroll;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * PayRoll main class
 * 
 * @author achuth
 *
 */
public class PayRollRunner {
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out
					.println("Usage: payroll <fully qualified input file> <fully qualified output file>");
			return;
		}
		Reader reader = new FileReader(args[0]);
		Writer writer = new FileWriter(args[1]);
		new PayRollEngine().processPayRolls(reader, writer);
	}
}
