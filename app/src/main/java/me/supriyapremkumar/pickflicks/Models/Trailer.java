package me.supriyapremkumar.pickflicks.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by supriya on 7/20/16.
 */
public class Trailer{
    String name;
    String size;
    String type;
    String src;

    public String getType(){
        return type;
    }

    public String getSrc(){
        return src;
    }
//    String backdropImage;
//    String releaseDate;
//    String rating;


//
//    public String getPosterPath() {
//        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
//    }
//
//    public String getOriginalTitle() {
//        return originalTitle;
//    }
//
//    public String getOverview() {
//        return overview;
//    }
//
//    public String getBackdropImage() {
//        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropImage);
//
//    }
//
//    public String getReleaseDate(){
//        return releaseDate;
//    }
//
//    public String getMovieRating(){
//        return rating;
//    }
//
//    public String getId(){
//        return id;
//    }

    public  Trailer(JSONObject jsonObject) throws JSONException{
        this.name = jsonObject.getString("name");
        this.size = jsonObject.getString("size");
        this.type = jsonObject.getString("type");
        this.src = jsonObject.getString("source");
//        this.backdropImage = jsonObject.getString("backdrop_path");
//        this.releaseDate = jsonObject.getString("release_date");
//        this.rating = jsonObject.getString("vote_average");

    }

    public static ArrayList<Trailer> fromJSONArray(JSONArray array){
        ArrayList<Trailer> results = new ArrayList<>();

        for(int x = 0; x < array.length(); x++){
            try {
                results.add(new Trailer(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }


}

