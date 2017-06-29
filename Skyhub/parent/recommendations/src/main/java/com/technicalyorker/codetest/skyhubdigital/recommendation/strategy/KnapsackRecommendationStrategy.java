package com.technicalyorker.codetest.skyhubdigital.recommendation.strategy;

import java.io.IOException;
import java.io.OutputStream;

import com.technicalyorker.codetest.skyhubdigital.recommendation.db.MovieDB;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.RecommendedMovies;

/**
 * 
 * @author achuth
 *
 */
public class KnapsackRecommendationStrategy implements RecommendationStrategy {
	public RecommendedMovies getBestRecommendation(Integer totalFlightTimeInMinutes) {
		RecommendedMovies recommendedMovies = knapsack(MovieDB.getMovieArray(), totalFlightTimeInMinutes);
		return recommendedMovies;
	}

	@Override
	public void printBestRecommendation(OutputStream os, Integer totalFlightTimeInMinutes) throws IOException {
		os.write(getBestRecommendation(totalFlightTimeInMinutes).toString().getBytes());
	}

	private RecommendedMovies knapsack(Movie[] movies, int W) {
		int N = movies.length;
		int[] w = new int[N];
		int[] v = new int[N];
		for (int i = 0; i < N; i++) {
			w[i] = v[i] = movies[i].getRuntime();
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
		final RecommendedMovies recommendation = new RecommendedMovies();
		int K = W;
		for (int i = N; i >= 1; i--)
			if (x[i][K] == 1) {
				recommendation.addMovies(movies[i - 1]);
				K -= w[i - 1];
			}
		return recommendation;
	}
}