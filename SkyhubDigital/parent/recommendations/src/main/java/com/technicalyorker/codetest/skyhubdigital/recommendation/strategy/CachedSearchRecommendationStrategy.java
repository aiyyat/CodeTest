package com.technicalyorker.codetest.skyhubdigital.recommendation.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.technicalyorker.codetest.skyhubdigital.recommendation.db.MovieDB;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.RecommendedMovies;

/**
 * 
 * @author achuth
 *
 */
public class CachedSearchRecommendationStrategy implements RecommendationStrategy {
	private static final Set<RecommendedMovies> recommendationMovieCache = new TreeSet<RecommendedMovies>();

	public CachedSearchRecommendationStrategy() {
		Movie[] movies = MovieDB.getMovieArray();
		int N = movies.length;
		for (int i = 1; i < N; i++) {
			loadCache(movies, N, i);
		}
		System.out.println("Loaded..");
	}

	public RecommendedMovies getBestRecommendation(Integer totalFlightTimeInMinutes) {
		for (RecommendedMovies recommendation : recommendationMovieCache) {
			if (recommendation.total() <= totalFlightTimeInMinutes) {
				return recommendation;
			}
		}
		return null;
	}

	@Override
	public void printBestRecommendation(OutputStream os, Integer totalFlightTimeInMinutes) throws IOException {
		for (RecommendedMovies recommendation : recommendationMovieCache) {
			if (recommendation.total() <= totalFlightTimeInMinutes) {
				os.write((recommendation.toString() + "\n").getBytes());
			}
		}
	}

	private void loadCache(Movie arr[], int n, int r, int index, Movie data[], int i) {
		if (index == r) {
			RecommendedMovies recommendedMovies = new RecommendedMovies();
			for (int j = 0; j < r; j++) {
				recommendedMovies.addMovies(data[j]);
			}
			recommendationMovieCache.add(recommendedMovies);
			return;
		}
		if (i >= n)
			return;
		data[index] = arr[i];
		loadCache(arr, n, r, index + 1, data, i + 1);
		loadCache(arr, n, r, index, data, i + 1);
	}

	private void loadCache(Movie arr[], int n, int r) {
		Movie data[] = new Movie[r];
		loadCache(arr, n, r, 0, data, 0);
	}
}