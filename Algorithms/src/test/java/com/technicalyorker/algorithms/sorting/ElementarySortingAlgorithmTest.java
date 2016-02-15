package com.technicalyorker.algorithms.sorting;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technicalyorker.algorithms.utilities.Util;

import junit.framework.TestCase;

public class ElementarySortingAlgorithmTest {
	int[] init;
	int[] shuffled;
	int size = 100000;

	public ElementarySortingAlgorithmTest() {
		init = Util.initialize(size);
		Shuffling.shuffle(init);
	}

	@Before
	public void setup() {
		shuffled = init.clone();
	}

	@After
	public void shutdown() {
		shuffled = init.clone();
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

	@Test
	public void testMerge() {
		testSort(new MergeSort());
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
