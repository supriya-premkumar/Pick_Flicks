package me.supriyapremkumar.pickflicks.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by supriya on 7/20/16.
 */
public class Movie {
    String id;
    String posterPath;
    String originalTitle;
    String overview;
    String backdropImage;
    String releaseDate;
    String rating;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropImage() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropImage);

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMovieRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString("id");
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropImage = jsonObject.getString("backdrop_path");
        this.releaseDate = jsonObject.getString("release_date");
        this.rating = jsonObject.getString("vote_average");

    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

}

