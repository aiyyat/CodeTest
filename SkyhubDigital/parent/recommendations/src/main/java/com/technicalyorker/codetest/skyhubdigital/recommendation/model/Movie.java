package com.technicalyorker.codetest.skyhubdigital.recommendation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author achuth
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Comparable<Movie> {
	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("runtime")
	private Integer runtime;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + ((runtime == null) ? 0 : runtime.hashCode());
		return result;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	// Default Behaviour is to be sorted by greater Runtime
	@Override
	public int compareTo(Movie o) {
		return -1 * this.getRuntime().compareTo(o.getRuntime());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (originalTitle == null) {
			if (other.originalTitle != null)
				return false;
		} else if (!originalTitle.equals(other.originalTitle))
			return false;
		if (runtime == null) {
			if (other.runtime != null)
				return false;
		} else if (!runtime.equals(other.runtime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [originalTitle=" + originalTitle + ", runtime=" + runtime + "]";
	}
}
