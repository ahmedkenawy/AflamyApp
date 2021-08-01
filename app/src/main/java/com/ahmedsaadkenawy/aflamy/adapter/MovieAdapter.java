package com.ahmedsaadkenawy.aflamy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedsaadkenawy.aflamy.R;
import com.ahmedsaadkenawy.aflamy.databinding.MovieCustomViewBinding;
import com.ahmedsaadkenawy.aflamy.model.Movie;
import com.ahmedsaadkenawy.aflamy.view.MovieActivity;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context context;
    // private ArrayList<Movie> list;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
        // this.list = list;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieCustomViewBinding view = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_custom_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
//        holder.name.setText(list.get(position).getOriginalTitle());
//        holder.rate.setText(Double.toString(list.get(position).getVoteCount()));
        Movie movie = getItem(position);
        String ImagePath = "https://image.tmdb.org/t/p/w500/" + getItem(position).getPosterPath();
        movie.setPosterPath(ImagePath);
        holder.movieCustomViewBinding.setMovie(movie);
//        Glide.with(context).load(ImagePath).error(R.drawable.ic_baseline_error_24).into(holder.poster);

    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
       public MovieCustomViewBinding movieCustomViewBinding;

        public MovieViewHolder(@NonNull MovieCustomViewBinding movieCustomViewBinding) {
            super(movieCustomViewBinding.getRoot());
            this.movieCustomViewBinding = movieCustomViewBinding;

            movieCustomViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Movie movie = getItem(position);
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", movie);
                    context.startActivity(intent);
                }
            });
        }
    }
}
