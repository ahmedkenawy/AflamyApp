package com.ahmedsaadkenawy.aflamy.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedsaadkenawy.aflamy.R;
import com.ahmedsaadkenawy.aflamy.adapter.MovieAdapter;
import com.ahmedsaadkenawy.aflamy.databinding.ActivityMainBinding;
import com.ahmedsaadkenawy.aflamy.model.Movie;
import com.ahmedsaadkenawy.aflamy.service.MovieDataService;
import com.ahmedsaadkenawy.aflamy.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieDataService dataService;
    private PagedList<Movie> results;
    private MovieAdapter adapter;
    RecyclerView rv;
    private MainActivityViewModel model;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        rv = binding.myRv;
        model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        getPopularMovies();
    }

    public void getPopularMovies() {
//        model.getMovie().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> movies) {
//                results= (ArrayList<Movie>) movies;
//                showRecyclerView();
//            }
//        });
        model.getPagedListLiveData().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movies = results;
                showRecyclerView();
            }
        });
    }

    private void showRecyclerView() {
        adapter = new MovieAdapter(this);
        rv.setAdapter(adapter);
        adapter.submitList(results);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();

    }

}