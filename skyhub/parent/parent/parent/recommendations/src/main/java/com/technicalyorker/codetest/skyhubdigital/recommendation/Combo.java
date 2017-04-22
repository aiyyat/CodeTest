package com.technicalyorker.codetest.skyhubdigital.recommendation;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.technicalyorker.codetest.skyhubdigital.recommendation.db.MovieDB;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

public class Combo {
	static int totalWeight = 100;

	public static void main(String[] args) {
		Movie arr[] = MovieDB.getMovies();
		int n = arr.length;
		for (int i = 1; i <= n; i++) {
			printCombination(arr, n, i);
		}
		print(325);
	}

	public static void print(int totalWeight) {
		System.out.println(totalWeight + "........");
		for (RecommendedMovies movies : movieCache) {
			if (movies.total() <= totalWeight) {
				System.out.println(movies);
			}
		}
	}

	static Set<RecommendedMovies> movieCache = new TreeSet<RecommendedMovies>();

	static void combinationUtil(Movie arr[], int n, int r, int index, Movie data[], int i) {
		if (index == r) {
			RecommendedMovies rM = new RecommendedMovies();
			for (int j = 0; j < r; j++) {
				rM.addMovies(data[j]);
			}
			movieCache.add(rM);
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= n)
			return;

		// current is included, put next at next location
		data[index] = arr[i];
		combinationUtil(arr, n, r, index + 1, data, i + 1);

		// current is excluded, replace it with next (Note that
		// i+1 is passed, but index is not changed)
		combinationUtil(arr, n, r, index, data, i + 1);
	}

	// The main function that prints all combinations of size r
	// in arr[] of size n. This function mainly uses combinationUtil()
	static void printCombination(Movie arr[], int n, int r) {
		// A temporary array to store all combination one by one
		Movie data[] = new Movie[r];

		// Print all combination using temprary array 'data[]'
		combinationUtil(arr, n, r, 0, data, 0);
	}
}

class RecommendedMovies implements Comparable<RecommendedMovies> {
	Set<Movie> movies = new TreeSet<>();

	public void addMovies(Movie movie) {
		movies.add(movie);
	}

	public Integer total() {
		int sum = 0;
		for (Movie movie : movies) {
			sum += movie.getRuntime();
		}
		return sum;
	}

	@Override
	public int compareTo(RecommendedMovies o) {
		int compare = -1 * total().compareTo(o.total());
		if (compare == 0) {
			if (o.movies.size() == movies.size()) {
				for (Iterator<Movie> thisitr = movies.iterator(), thatitr = o.movies.iterator(); thisitr.hasNext();) {
					Movie thisMovie = thisitr.next();
					Movie thatMovie = thatitr.next();
					compare = thisMovie.compareTo(thatMovie);
					if (compare == 0) {
						compare = thisMovie.getOriginalTitle().compareTo(thatMovie.getOriginalTitle());
						if (compare != 0) {
							return compare;
						} // else goto the next movie
					} else {
						return compare;
					}
				}
				return -1;
			} else {
				return -1 * (movies.size() - o.movies.size());
			}
		}
		return compare;
	}

	@Override
	public String toString() {
		return "RecommendedMovies [total()=" + total() + ", movies=" + movies + "]";
	}
}