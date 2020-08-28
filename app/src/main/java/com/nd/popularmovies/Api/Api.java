package com.nd.popularmovies.Api;

import com.nd.popularmovies.Models.MovieDataResponse;
import com.nd.popularmovies.Models.TrailerDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("{sortBy}")
    Call<MovieDataResponse> getMovieData(
            @Path("sortBy") String sortby,
            @Query("api_key") String apiKey
    );

    @GET("{movieId}/videos")
    Call<TrailerDataResponse> getTrailerData(
            @Path("movieId") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("{movieId}/reviews")
    Call<TrailerDataResponse> getReviews(
            @Path("movieId") String movieId,
            @Query("api_key") String apiKey
    );

}
