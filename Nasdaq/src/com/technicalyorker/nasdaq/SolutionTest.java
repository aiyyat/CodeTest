package com.technicalyorker.nasdaq;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	Solution s = new Solution();

	@Test
	public void testQuestionUseCase() {
		TestCase.assertEquals(21, s.perform(5, 2).intValue());
	}

	@Test
	public void testMyUseCase1() {
		TestCase.assertEquals(33, s.perform(5, 4).intValue());
	}
}
