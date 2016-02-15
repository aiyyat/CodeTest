package com.technicalyorker.algorithms.sorting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technicalyorker.algorithms.utilities.Util;

import junit.framework.TestCase;

public class HeapTest {
	int[] init;
	int[] shuffled;
	int size = 100000;

	public HeapTest() {
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

	@Test
	public void testHeapSort() {
		HeapSort h = new HeapSort(size);
		for (int i : shuffled) {
			h.insert(i);
		}
		for (int i = size - 1; i >= 0; i--) {
			System.out.println(i);
			TestCase.assertEquals(i, h.removeFirst());
		}
	}
}
