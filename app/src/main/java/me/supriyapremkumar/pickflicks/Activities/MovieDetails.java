package me.supriyapremkumar.pickflicks.Activities;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.supriyapremkumar.pickflicks.Adapter.MovieArrayAdapter;
import me.supriyapremkumar.pickflicks.Models.Movie;

/**
 * Created by supriya on 7/22/16.
 */
public class MovieDetails {
    private ArrayList<Movie> movies;
    private MovieArrayAdapter movieAdapter;

    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView overView;
    private TextView releaseDate;
    private TextView rating;



}
