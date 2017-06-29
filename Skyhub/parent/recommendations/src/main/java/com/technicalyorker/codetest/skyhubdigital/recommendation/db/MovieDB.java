package com.technicalyorker.codetest.skyhubdigital.recommendation.db;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.Movie;
import com.technicalyorker.codetest.skyhubdigital.recommendation.model.MovieList;

/**
 * 
 * @author achuth
 *
 */
public class MovieDB {
	public static Movie[] getMovieArray() {
		MovieList movieList = getMovieList();
		Movie[] movies = (movieList.getMovies().toArray(new Movie[0]));
		Arrays.sort(movies);
		return movies;
	}

	public static MovieList getMovieList() {
		MovieList movieList = new MovieList();
		ObjectMapper mapper = new ObjectMapper();
		try {
			movieList = mapper.readValue(new File(MovieDB.class.getClassLoader().getResource("movies.json").getFile()),
					MovieList.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieList;
	}
}
