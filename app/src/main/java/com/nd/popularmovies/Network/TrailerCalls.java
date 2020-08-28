package com.nd.popularmovies.Network;

import android.content.Context;
import android.widget.Toast;

import com.nd.popularmovies.Api.RetrofitClient;
import com.nd.popularmovies.BuildConfig;
import com.nd.popularmovies.Models.MovieData;
import com.nd.popularmovies.Models.TrailerData;
import com.nd.popularmovies.Models.TrailerDataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerCalls {

    public static void apiCallTrailerData(Context context, String movieId) {
        Call<TrailerDataResponse> call = RetrofitClient
                .getInstance(context)
                .getApi()
                .getTrailerData(movieId, BuildConfig.API_KEY);
        call.enqueue(new Callback<TrailerDataResponse>() {
            @Override
            public void onResponse(Call<TrailerDataResponse> call, Response<TrailerDataResponse> response) {
                List<TrailerData> trailerData = response.body().getResults();
                TrailerData.settList(trailerData);
                TrailerCalls.getTrailerData(movieId);
            }

            @Override
            public void onFailure(Call<TrailerDataResponse> call, Throwable t) {
                Toast.makeText(context, "Unable to get Trailer link", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getTrailerData(String movieID) {
        List<MovieData> movieList = MovieData.getfList();
        for (MovieData movieData : movieList)
            if (movieData.getId().equals(movieID)) {
                List<TrailerData> trailerDataList = TrailerData.gettList();
                for (TrailerData trailerData : trailerDataList)
                    if (trailerData.getType().equals("Trailer")) {
                        movieData.setYoutubeLink(trailerData.getKey());
                        break;
                    }
                break;
            }
    }

}
