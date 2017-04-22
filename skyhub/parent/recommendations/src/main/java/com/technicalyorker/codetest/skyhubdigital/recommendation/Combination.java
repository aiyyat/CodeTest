package com.technicalyorker.codetest.skyhubdigital.recommendation;

import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

public class Combination {
	public void perform(Movie[] movies, int W) {
		
	}
	Map<Integer,Set<List<Movies>> movies
	static void combinationUtil(int arr[], int n, int r, int index, int data[], int i, int w) {
		// Current combination is ready to be printed, print it
		if (index == r) {
			Integer sum = 0;
			for (int j = 0; j < r; j++)
				
			System.out.println("");
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= n)
			return;

		// current is included, put next at next location
		data[index] = arr[i];
		combinationUtil(arr, n, r, index + 1, data, i + 1, w);

		// current is excluded, replace it with next (Note that
		// i+1 is passed, but index is not changed)
		combinationUtil(arr, n, r, index, data, i + 1, w);
	}

	// The main function that prints all combinations of size r
	// in arr[] of size n. This function mainly uses combinationUtil()
	static void printCombination(int arr[], int n, int r, int W) {
		// A temporary array to store all combination one by one
		int data[] = new int[r];

		// Print all combination using temprary array 'data[]'
		combinationUtil(arr, n, r, 0, data, 0, W);
	}
}
