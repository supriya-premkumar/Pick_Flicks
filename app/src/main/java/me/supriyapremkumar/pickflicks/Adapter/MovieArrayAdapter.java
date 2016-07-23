package me.supriyapremkumar.pickflicks.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import me.supriyapremkumar.pickflicks.Models.Movie;
import me.supriyapremkumar.pickflicks.R;

/**
 * Created by supriya on 7/20/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    // View lookup cache
    private static class ViewHolder {
        ImageView ivMovie;
        TextView tvTitle;
        TextView overView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        Movie movie = getItem(position);

        //check the existing view is being reused
        ViewHolder viewHolder;          //view lookup cache stored in tag
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_item_movie, parent, false);
            viewHolder.ivMovie = (ImageView)convertView.findViewById(R.id.movieImage);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.overView = (TextView)convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //populate the data into the template view using the data object
        viewHolder.ivMovie.setImageResource(0);
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.overView.setText(movie.getOverview());


        boolean isLandscape = getContext().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        if(isLandscape){
            Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).into(viewHolder.ivMovie);
        }else {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).into(viewHolder.ivMovie);
        }
        return convertView;


    }
}
