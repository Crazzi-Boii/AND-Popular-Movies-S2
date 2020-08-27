package com.nd.popularmovies.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieData {

    private static boolean isUpdated = false;
    private static List<MovieData> fList = null;
    private static MovieData movieData;

    @SerializedName("title")
    @Expose
    private String movieTitle;
    @SerializedName("original_title")
    @Expose
    private String movieOriginalTitle;
    @SerializedName("poster_path")
    @Expose
    private String moviePoster;
    @SerializedName("backdrop_path")
    @Expose
    private String movieImage_optional;
    @SerializedName("overview")
    @Expose
    private String movieOverview;
    @SerializedName("vote_average")
    @Expose
    private String movieUserRating;
    @SerializedName("release_date")
    @Expose
    private String movieReleaseDate;

    public MovieData(String movieTitle, String movieOriginalTitle, String moviePoster, String movieImage_optional, String movieOverview, String movieUserRating, String movieReleaseDate) {
        this.movieTitle = movieTitle;
        this.movieOriginalTitle = movieOriginalTitle;
        this.moviePoster = moviePoster;
        this.movieImage_optional = movieImage_optional;
        this.movieOverview = movieOverview;
        this.movieUserRating = movieUserRating;
        this.movieReleaseDate = movieReleaseDate;
    }

    public static boolean isUpdated() {
        return isUpdated;
    }

    public static List<MovieData> getfList() {
        return fList;
    }

    public static void setfList(List<MovieData> fList) {
        if (fList == null) {
            MovieData.fList = fList;
            MovieData.isUpdated = true;
        }

    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getMovieImage_optional() {
        return movieImage_optional;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMovieUserRating() {
        return movieUserRating;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public static MovieData getMovieData() {
        return movieData;
    }

    public static void setMovieData(MovieData movieData) {
        MovieData.movieData = movieData;
    }

}
