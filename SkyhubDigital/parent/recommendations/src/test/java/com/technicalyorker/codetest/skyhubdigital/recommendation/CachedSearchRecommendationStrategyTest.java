package com.technicalyorker.codetest.skyhubdigital.recommendation;

import org.junit.BeforeClass;
import org.junit.Test;

import com.technicalyorker.codetest.skyhubdigital.recommendation.strategy.CachedSearchRecommendationStrategy;

import junit.framework.TestCase;

/**
 * 
 * @author achuth
 *
 */
public class CachedSearchRecommendationStrategyTest {
	static CachedSearchRecommendationStrategy strategy;

	@BeforeClass
	public static void load() {
		strategy = new CachedSearchRecommendationStrategy();
	}

	@Test
	public void test72() {
		TestCase.assertEquals(
				"RecommendedMovies [total()=72, movies=[Movie [originalTitle=Batman: The Killing Joke, runtime=72]]]",
				strategy.getBestRecommendation(72).toString());
	}

	@Test
	public void test325() {
		TestCase.assertEquals(
				"RecommendedMovies [total()=325, movies=[Movie [originalTitle=Suicide Squad, runtime=130], Movie [originalTitle=Jason Bourne, runtime=123], Movie [originalTitle=Batman: The Killing Joke, runtime=72]]]",
				strategy.getBestRecommendation(325).toString());
	}

	@Test
	public void printAllUnder325() {
		System.out.println("-----------------325-----------------");
		strategy.consolePrintBestRecommendation(325);
	}
}