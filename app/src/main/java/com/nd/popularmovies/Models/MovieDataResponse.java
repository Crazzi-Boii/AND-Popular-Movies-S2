
package com.nd.popularmovies.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDataResponse {

    @SerializedName("results")
    @Expose
    private List<MovieData> movieDataResults;

    public MovieDataResponse(List<MovieData> movieDataResults) {
        this.movieDataResults = movieDataResults;
    }

    public List<MovieData> getMovieDataResults() {
        return movieDataResults;
    }

}

