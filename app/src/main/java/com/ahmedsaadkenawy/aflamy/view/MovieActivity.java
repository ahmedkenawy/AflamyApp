package com.ahmedsaadkenawy.aflamy.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ahmedsaadkenawy.aflamy.R;
import com.ahmedsaadkenawy.aflamy.model.Movie;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MovieActivity extends AppCompatActivity {
    ImageView poster;
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        poster=findViewById(R.id.poster);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            Movie movie = getIntent().getParcelableExtra("movie");
            String ImagePath = "https://image.tmdb.org/t/p/w500/" + movie.getPosterPath();
            Glide.with(this).load(ImagePath).into(poster);
            tv1.setText(movie.getOriginalTitle());
            tv2.setText(Double.toString(movie.getVoteAverage()));
            tv3.setText(movie.getReleaseDate());
        }


    }
}