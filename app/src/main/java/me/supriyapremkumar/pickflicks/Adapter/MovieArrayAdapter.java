package me.supriyapremkumar.pickflicks.Adapter;

import android.content.Context;
import android.content.Intent;
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
import me.supriyapremkumar.pickflicks.Activities.PlayTrailerActivity;
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

        ImageView trailer;
        ImageView movieIcon;
        TextView movieOverView;
        TextView movieTitle;
        TextView rating;
        TextView releaseDate;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getItemViewType(int pos){
        if (Float.valueOf(getItem(pos).getMovieRating()) > 5) {
            return 1;
        }
        else {
            return 0;
        }
        //return getItem(pos).getMovieRating();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        final Movie movie = getItem(position);

        FoldingCell cell = (FoldingCell) convertView;
        //check the existing view is being reused
        final ViewHolder foldedViewHolder;          //view lookup cache stored in tag
        int type = getItemViewType(position);
        if (type == 0) {
            if(cell == null){
                foldedViewHolder = new ViewHolder();

                cell = getHeterogeneousView(parent, type);
                foldedViewHolder.ivMovie = (ImageView)cell.findViewById(R.id.movieImage);
                foldedViewHolder.tvTitle = (TextView)cell.findViewById(R.id.tvTitle);
                foldedViewHolder.overView = (TextView)cell.findViewById(R.id.tvOverview);

                foldedViewHolder.movieIcon = (ImageView)cell.findViewById(R.id.movie_icon);
                foldedViewHolder.movieTitle = (TextView)cell.findViewById(R.id.movie_title);

                foldedViewHolder.trailer = (ImageView) cell.findViewById(R.id.head_image);

                foldedViewHolder.movieOverView = (TextView)cell.findViewById(R.id.content_overView);
                foldedViewHolder.releaseDate = (TextView)cell.findViewById(R.id.date);
                foldedViewHolder.rating = (TextView)cell.findViewById(R.id.rating);

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

            foldedViewHolder.movieIcon.setImageResource(0);
            foldedViewHolder.movieTitle.setText(movie.getOriginalTitle());
            foldedViewHolder.trailer.setImageResource(0);
            foldedViewHolder.trailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), PlayTrailerActivity.class);
                    intent.putExtra("movie_id", movie.getId());
                    getContext().startActivity(intent);

                }
            });

            foldedViewHolder.movieOverView.setText(movie.getOverview());
            foldedViewHolder.releaseDate.setText(movie.getReleaseDate());
            foldedViewHolder.rating.setText(movie.getMovieRating());

            final boolean isLandscape = getContext().getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE;
            if(isLandscape){
                Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).into(foldedViewHolder.ivMovie);
                Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).resize(2048, 768).into(foldedViewHolder.trailer);
                Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).resize(256,256).into(foldedViewHolder.movieIcon);

            }else {
                Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).into(foldedViewHolder.ivMovie);
                Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).resize(1024, 512).into(foldedViewHolder.trailer);
                Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).resize(256,256).into(foldedViewHolder.movieIcon);
            }
            return cell;

        }

        else {
            if(cell == null){
                foldedViewHolder = new ViewHolder();

                cell = getHeterogeneousView(parent, type);
                foldedViewHolder.ivMovie = (ImageView)cell.findViewById(R.id.movieImage);



//                foldedViewHolder.movieIcon = (ImageView)cell.findViewById(R.id.movie_icon);
//                foldedViewHolder.movieTitle = (TextView)cell.findViewById(R.id.movie_title);
//
//                foldedViewHolder.trailer = (ImageView) cell.findViewById(R.id.head_image);
//
//                foldedViewHolder.movieOverView = (TextView)cell.findViewById(R.id.content_overView);
//                foldedViewHolder.releaseDate = (TextView)cell.findViewById(R.id.date);
//                foldedViewHolder.rating = (TextView)cell.findViewById(R.id.rating);

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
            foldedViewHolder.ivMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), PlayTrailerActivity.class);
                    intent.putExtra("movie_id", movie.getId());
                    getContext().startActivity(intent);

                }
            });

            final boolean isLandscape = getContext().getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE;
            if(isLandscape){
                Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).into(foldedViewHolder.ivMovie);
                //Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).resize(1024, 512).into(foldedViewHolder.trailer);
                //Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).resize(256,256).into(foldedViewHolder.movieIcon);

            }else {
                Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(20,20)).resize(1024, 512).into(foldedViewHolder.ivMovie);
                //Picasso.with(getContext()).load(movie.getBackdropImage()).transform(new RoundedCornersTransformation(10,10)).resize(1024, 512).into(foldedViewHolder.trailer);
                //Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(20,20)).resize(256,256).into(foldedViewHolder.movieIcon);
            }
            return cell;
        }



    }

    private FoldingCell getHeterogeneousView(ViewGroup parent, int type) {

        if(type == 0){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            FoldingCell cell = (FoldingCell)inflater.inflate(R.layout.cell, parent, false);
            return cell;
        }

        else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            FoldingCell cell = (FoldingCell) inflater.inflate(R.layout.popular_movies, parent, false);
            return cell;
        }

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
