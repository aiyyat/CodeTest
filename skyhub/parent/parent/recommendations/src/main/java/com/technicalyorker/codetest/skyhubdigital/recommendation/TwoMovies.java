package com.technicalyorker.codetest.skyhubdigital.recommendation;

import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;

public class TwoMovies implements Comparable<TwoMovies> {
	private Movie movie1;
	private Movie movie2;

	public TwoMovies(Movie movie1, Movie movie2) {
		super();
		if (movie1.getOriginalTitle().compareTo(movie2.getOriginalTitle()) > 0) {
			this.movie2 = movie1;
			this.movie1 = movie2;
			return;
		}
		this.movie1 = movie1;
		this.movie2 = movie2;
	}

	public Integer getTime() {
		return movie1.getRuntime() + movie2.getRuntime();
	}

	@Override
	public int compareTo(TwoMovies anotherTwoMovies) {
		Integer compare = anotherTwoMovies.getTime().compareTo(this.getTime());
		if (compare == 0) {
			return (anotherTwoMovies.movie1.getOriginalTitle() + anotherTwoMovies.movie2.getOriginalTitle())
					.compareTo(this.movie1.getOriginalTitle() + this.movie2.getOriginalTitle());
		}
		return compare;
	}

	@Override
	public String toString() {
		return movie1.getOriginalTitle() + "(" + movie1.getRuntime() + ")  " + movie2.getOriginalTitle() + "("
				+ movie2.getRuntime() + ")   " + getTime();
	}

	public Movie getMovie1() {
		return movie1;
	}

	public void setMovie1(Movie movie1) {
		this.movie1 = movie1;
	}

	public Movie getMovie2() {
		return movie2;
	}

	public void setMovie2(Movie movie2) {
		this.movie2 = movie2;
	}
}