package com.technicalyorker.algorithms.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.technicalyorker.algorithms.sorting.Shuffling;

public class InputUtil {
	public static void main(String[] args) throws IOException {
		int size = 100;
		int count = 10;
		BufferedWriter w = new BufferedWriter(
				new FileWriter("/home/achuth/git/misc/Algorithms/src/test/resources/input.txt"));
		int[] a = Util.initialize(100);
		w.write(count + "\n");
		w.write(size + "\n");
		for (int j = 0; j < count; j++) {
			Shuffling.shuffle(a);
			for (int i = 0; i < size; i++) {
				w.write("" + a[i]);
				if (i < a.length - 1) {
					w.write(" ");
				}
			}
			if (j < count - 1) {
				w.write("\n");
			}
		}
		w.close();
	}
}
