package com.technicalyorker.codetest.skyhubdigital.recommendation.strategy;

import java.io.OutputStream;
import java.util.Set;

import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

public interface RecommendationStrategy {
	public Set<Movie> getBestRecommendation(Integer totalFlightTimeInMinutes);

	public void printBestRecommendation(OutputStream os, Integer totalFlightTimeInMinutes);
}
