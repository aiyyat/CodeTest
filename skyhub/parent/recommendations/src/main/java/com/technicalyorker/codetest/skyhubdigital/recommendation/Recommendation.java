package com.technicalyorker.codetest.skyhubdigital.recommendation;

import org.junit.Test;

import com.technicalyorker.codetest.skyhubdigital.recommendation.db.MovieDB;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;
import com.technicalyorker.codetest.skyhubdigital.recommendation.strategy.KnapsackRecommendationStrategy;

/**
 * 
 * @author achuth
 *
 */
public class Recommendation {
	@Test
	public void doTest() {
		Movie[] movies = MovieDB.getMovies();
		new KnapsackRecommendationStrategy().perform(movies, 325);
		// new Knapsack().perform(movies, 92);
	}
}