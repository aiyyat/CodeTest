package com.technicalyorker.codetest.skyhubdigital.recommendation.strategy;

import java.io.IOException;
import java.io.OutputStream;

import com.technicalyorker.codetest.skyhubdigital.recommendation.model.RecommendedMovies;

/**
 * 
 * @author achuth
 *
 */
public interface RecommendationStrategy {
	default public void consolePrintBestRecommendation(Integer totalFlightTimeInMinutes) {
		try {
			printBestRecommendation(System.out, totalFlightTimeInMinutes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RecommendedMovies getBestRecommendation(Integer totalFlightTimeInMinutes);

	public void printBestRecommendation(OutputStream os, Integer totalFlightTimeInMinutes) throws IOException;
}
