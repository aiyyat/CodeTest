package com.technicalyorker.codetest.skyhubdigital.recommendation;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.technicalyorker.codetest.skyhubdigital.recommendation.db.MovieDB;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

/**
 * 
 * @author achuth
 *
 */
public class Recommendation {
	public void display(Integer flightTime) {
		for (TwoMovies movie : prepareMovieList(flightTime)) {
			System.out.println(movie);
		}
	}

	public Set<TwoMovies> prepareMovieList(Integer flightTime) {
		List<Movie> movies = MovieDB.getMovieList();
		Set<TwoMovies> set = new TreeSet<>();
		for (Movie movie1 : movies) {
			for (Movie movie2 : movies) {
				TwoMovies twoMovie = new TwoMovies(movie1, movie2);
				if (movie1 != movie2 && !set.contains(twoMovie)) {
					if (flightTime > twoMovie.getTime()) {
						set.add(twoMovie);
					}
				}
			}
		}
		return set;
	}
}
