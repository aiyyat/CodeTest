package com.technicalyorker.codetest.skyhubdigital.recommendation;

import org.junit.Test;

import com.technicalyorker.codetest.skyhubdigital.recommendation.db.MovieDB;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

/**
 * 
 * @author achuth
 *
 */
public class Recommendation {
	@Test
	public void doTest() {
		Movie[] movies = MovieDB.getMovies();
		new Knapsack().perform(movies, 325);
		// new Knapsack().perform(movies, 92);
	}
}