package com.technicalyorker.codetest.skyhubdigital.recommendation;

import org.junit.Test;

import com.technicalyorker.codetest.skyhubdigital.recommendation.strategy.KnapsackRecommendationStrategy;

import junit.framework.TestCase;

/**
 * 
 * @author achuth
 *
 */
public class KnapsackRecommendationStrategyTest {
	KnapsackRecommendationStrategy strategy = new KnapsackRecommendationStrategy();

	@Test
	public void testMinimum() {
		TestCase.assertEquals(
				"RecommendedMovies [total()=72, movies=[Movie [originalTitle=Batman: The Killing Joke, runtime=72]]]",
				strategy.getBestRecommendation(72).toString());
	}

	@Test
	public void test325() {
		TestCase.assertEquals(325, strategy.getBestRecommendation(325).total().intValue());
	}
}