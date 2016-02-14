package com.technicalyorker.algorithms.sorting;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.technicalyorker.algorithms.utilities.Util;

import junit.framework.TestCase;

public class SortingTest {
	int[] shuffled;
	int size = 60000;

	@Before
	public void init() {
		shuffled = Util.initialize(size);
		Shuffling.shuffle(shuffled);
	}

	@Test(expected = AssertionError.class)
	public void testShuffling() {
		TestCase.assertTrue(Util.checkSorted(shuffled));
	}

	@Test
	public void testInsertion() {
		testSort(new InsertionSort());
	}

	@Test
	public void testSelection() {
		testSort(new SelectionSort());
	}

	@Test
	public void testBubble() {
		testSort(new BubbleSort());
	}

	@Test
	public void testQuick() {
		testSort(new QuickSort());
	}

	private void testSort(Sortable algorithm) {
		Calendar then = Calendar.getInstance();
		algorithm.sort(shuffled);
		Calendar now = Calendar.getInstance();
		TestCase.assertEquals(Util.asString(Util.initialize(size)), Util.asString(shuffled));
		System.out.println(algorithm.getClass().getSimpleName() + ":"
				+ (now.getTimeInMillis() - then.getTimeInMillis()) / 1000.0 + "s");
	}
}
