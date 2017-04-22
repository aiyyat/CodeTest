package com.technicalyorker.codetest.skyhubdigital.recommendation.strategy;

import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

public class KnapsackRecommendationStrategy implements RecommendationStrategy{
	public void perform(Movie[] movies, int W) {
		int N = movies.length;
		int[] w = new int[N];
		int[] v = new int[N];
		System.out.println("Movie List:");
		for (int i = 0; i < N; i++) {
			w[i] = v[i] = movies[i].getRuntime();
			System.out.printf("%s %s\n", movies[i].getOriginalTitle(), movies[i].getRuntime());
		}
		int[][] V = new int[N + 1][W + 1];
		for (int i = 0; i <= W; i++)
			V[0][i] = 0;
		int x[][] = new int[N + 1][W + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if ((w[i - 1] <= j) && (v[i - 1] + V[i - 1][j - w[i - 1]] > V[i - 1][j])) {
					V[i][j] = v[i - 1] + V[i - 1][j - w[i - 1]];
					x[i][j] = 1;
				} else {
					V[i][j] = V[i - 1][j];
					x[i][j] = 0;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				System.out.print(x[i][j] + "\t");
			}
			System.out.println();
		}
		// backtracking
		System.out.println("\n\nRecommendation:");

		int K = W;
		for (int i = N; i >= 1; i--)
			if (x[i][K] == 1) {
				System.out.println(movies[i - 1]);
				K -= w[i - 1];
			}
		System.out.println("Total Duration: " + V[N][W]);
	}
}