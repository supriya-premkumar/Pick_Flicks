package me.supriyapremkumar.pickflicks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.supriyapremkumar.pickflicks.Models.Movie;
import me.supriyapremkumar.pickflicks.R;

/**
 * Created by supriya on 7/20/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        Movie movie = getItem(position);

        //check the existing view is being reused
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_item_movie, parent, false);
        }

        //find image view
        ImageView imageView = (ImageView)convertView.findViewById(R.id.movieImage);

        //clear out image from convertview
        imageView.setImageResource(0);

        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);

        //populate the data
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(imageView);

        return convertView;


    }
}
