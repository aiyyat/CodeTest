package com.jpmc.problem1;

import java.io.FileNotFoundException;

import org.junit.Test;

public class JPMCProblem1Test {

	/**
	 * This is the Test Case of Concern.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void actualJPMCQuestion() throws FileNotFoundException {
		TriangleProblem defaultInput = new TriangleProblem(getClass()
				.getResourceAsStream("/data.txt"));
		System.out
				.println("JPMC Question Solution:" + defaultInput.getMaxSum());
	}
}
