package me.supriyapremkumar.pickflicks.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import me.supriyapremkumar.pickflicks.Models.Movie;
import me.supriyapremkumar.pickflicks.R;

/**
 * Created by supriya on 7/20/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();


    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    // View lookup cache
    private static class ViewHolder {
        ImageView ivMovie;
        TextView tvTitle;
        TextView overView;

        ImageView movieImage;
        ImageView movieIcon;
        TextView movieOverView;
        TextView movieTitle;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        Movie movie = getItem(position);
        FoldingCell cell = (FoldingCell) convertView;
        //check the existing view is being reused
        ViewHolder foldedViewHolder;          //view lookup cache stored in tag

        if(cell == null){
            foldedViewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            cell = (FoldingCell)inflater.inflate(R.layout.cell, parent, false);
            foldedViewHolder.ivMovie = (ImageView)cell.findViewById(R.id.movieImage);
            foldedViewHolder.tvTitle = (TextView)cell.findViewById(R.id.tvTitle);
            foldedViewHolder.overView = (TextView)cell.findViewById(R.id.tvOverview);

            foldedViewHolder.movieIcon = (ImageView)cell.findViewById(R.id.movie_icon);
            foldedViewHolder.movieTitle = (TextView)cell.findViewById(R.id.movie_title);
            foldedViewHolder.movieImage = (ImageView)cell.findViewById(R.id.head_image);
            foldedViewHolder.movieOverView = (TextView)cell.findViewById(R.id.content_overView);

            cell.setTag(foldedViewHolder);
        } else{
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
           foldedViewHolder = (ViewHolder)cell.getTag();
        }

        //populate the data into the template view using the data object
        foldedViewHolder.ivMovie.setImageResource(0);
        foldedViewHolder.tvTitle.setText(movie.getOriginalTitle());
        foldedViewHolder.overView.setText(movie.getOverview());


        foldedViewHolder.movieImage.setImageResource(0);
        foldedViewHolder.movieIcon.setImageResource(0);
        foldedViewHolder.movieTitle.setText(movie.getOriginalTitle());

        foldedViewHolder.movieOverView.setText(movie.getOverview());

        boolean isLandscape = getContext().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        if(isLandscape){
            Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).into(foldedViewHolder.ivMovie);
            Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).resize(1024, 512).into(foldedViewHolder.movieImage);
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).resize(256,256).into(foldedViewHolder.movieIcon);

        }else {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).into(foldedViewHolder.ivMovie);
            Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).resize(1024, 512).into(foldedViewHolder.movieImage);
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).resize(256,256).into(foldedViewHolder.movieIcon);
        }
        return cell;

    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }
}
