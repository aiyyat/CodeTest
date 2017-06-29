package com.technicalyorker.codetest.skyhubdigital.recommendation.model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author achuth
 *
 */
public class RecommendedMovies implements Comparable<RecommendedMovies> {
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
						continue;
					} else {
						return compare;
					}
				}
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