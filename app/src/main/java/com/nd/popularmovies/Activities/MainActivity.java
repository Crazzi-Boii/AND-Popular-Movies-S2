package com.nd.popularmovies.Activities;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nd.popularmovies.Network.RetrofitNetworkCalls;
import com.nd.popularmovies.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView errTextView;
    private View view;
    private int selectedFlag = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.loading_indicator_pb);
        errTextView = findViewById(R.id.error_msg_tv);
        recyclerView = findViewById(R.id.recyclerview_movieFront);
        view = getWindow().getDecorView().getRootView();

        if (checkInternetConnectivity()) {
            showBuffer();
            RetrofitNetworkCalls.apiCallPopular(this, view);
        } else
            showErr();
    }

    // Checking Internet Connection
    private boolean checkInternetConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //  HANDLING MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.popular_movies:
                selectedFlag = 1;
                makeCall();
                return true;
            case R.id.mosted_rated:
                selectedFlag = 2;
                makeCall();
                return true;
            case R.id.Action_Refersh:
                makeCall();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Polishing UI
    private void showErr() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        errTextView.setVisibility(View.VISIBLE);
    }

    private void showBuffer() {
        errTextView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    // Making Network calls
    private void makeCall() {
        if (selectedFlag == 1)
            if (checkInternetConnectivity()) {
                showBuffer();
                RetrofitNetworkCalls.apiCallPopular(this, view);
            } else
                showErr();
        if (selectedFlag == 2)
            if (checkInternetConnectivity()) {
                showBuffer();
                RetrofitNetworkCalls.apiCallMostRated(this, view);
            } else
                showErr();
    }
}