package com.nd.popularmovies.Network;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nd.popularmovies.Activities.MovieDetails;
import com.nd.popularmovies.Adapters.MovieListAdapter;
import com.nd.popularmovies.Api.RetrofitClient;
import com.nd.popularmovies.BuildConfig;
import com.nd.popularmovies.Models.MovieData;
import com.nd.popularmovies.Models.MovieDataResponse;
import com.nd.popularmovies.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitNetworkCalls {


    public static void apiCallPopular(Context context, View view) {


        Call<MovieDataResponse> call = RetrofitClient
                .getInstance(context)
                .getApi()
                .getMovieData(context.getString(R.string.Base_URL_Popular), BuildConfig.API_KEY);

        call.enqueue(new Callback<MovieDataResponse>() {
            @Override
            public void onResponse(Call<MovieDataResponse> call, Response<MovieDataResponse> response) {
                List<MovieData> movieData = response.body().getMovieDataResults();
                MovieData.setfList(movieData);
                RetrofitNetworkCalls.initRecyclerView(view, context, movieData);
            }
            @Override
            public void onFailure(Call<MovieDataResponse> call, Throwable t) {
                Toast.makeText(context, "Refresh Again", Toast.LENGTH_LONG).show();
                //Log.e( "onFailure: ", t.getMessage());
            }
        });

    }

    public static void apiCallMostRated(Context context, View view) {
        Call<MovieDataResponse> call = RetrofitClient
                .getInstance(context)
                .getApi()
                .getMovieData(context.getString(R.string.Base_URL_Top_Rated), BuildConfig.API_KEY);

        call.enqueue(new Callback<MovieDataResponse>() {
            @Override
            public void onResponse(Call<MovieDataResponse> call, Response<MovieDataResponse> response) {
                List<MovieData> movieData = response.body().getMovieDataResults();
                MovieData.setfList(movieData);
                //MovieListAdapter.setA(movieData);
                RetrofitNetworkCalls.initRecyclerView(view, context, movieData);
            }

            @Override
            public void onFailure(Call<MovieDataResponse> call, Throwable t) {
                Toast.makeText(context, "Refresh Again", Toast.LENGTH_LONG).show();
            }
        });
    }

    //  RECYCLER VIEW INITIALIZED
    static void initRecyclerView(View view, Context context, List<MovieData> movieData) {
        ProgressBar progressBar = view.findViewById(R.id.loading_indicator_pb);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_movieFront);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MovieListAdapter movieListAdapter = new MovieListAdapter(movieData, new MovieListAdapter.MovieListAdapterOnClickHandler() {
            @Override
            public void onClick(MovieData movieData) {
                //Toast.makeText(context, movieData.getMovieOriginalTitle(), Toast.LENGTH_SHORT).show();
                MovieData.setMovieData(movieData);
                Intent intent = new Intent(context, MovieDetails.class);
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(movieListAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

    }
}
