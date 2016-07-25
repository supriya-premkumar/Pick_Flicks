package me.supriyapremkumar.pickflicks.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.supriyapremkumar.pickflicks.R;
import me.supriyapremkumar.pickflicks.databinding.ActivityTicketsBookingBinding;

public class TicketsBookingActivity extends AppCompatActivity {
    // Store the binding
    private ActivityTicketsBookingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String movieName = getIntent().getStringExtra("movie_name");
        String movieRatings = getIntent().getStringExtra("movie_ratings");
        String movieOverview = getIntent().getStringExtra("movie_overview");
        // Inflate the content view (replacing `setContentView`)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tickets_booking);

        TextView movie_name = binding.movieName;
        TextView movie_overview = binding.movieOverview;
        TextView movie_ratings = binding.movieRating;

        movie_name.setText(movieName);
        movie_overview.setText(movieOverview);
        movie_ratings.setText(movieRatings);


    }
}
