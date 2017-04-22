package com.technicalyorker.codetest.skyhubdigital.recommendation.model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
	private List<Movie> movies = new ArrayList<>();

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "MovieList [movies=" + movies + "]";
	}
}
