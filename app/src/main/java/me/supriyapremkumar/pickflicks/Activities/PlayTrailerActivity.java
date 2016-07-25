package me.supriyapremkumar.pickflicks.Activities;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import me.supriyapremkumar.pickflicks.Models.Trailer;
import me.supriyapremkumar.pickflicks.R;

public class PlayTrailerActivity extends YouTubeBaseActivity{
    private AsyncHttpClient client = new AsyncHttpClient();
    //private String trailerSrc = new String();
    private JSONArray trailerJsonResults = new JSONArray();
    private ArrayList<Trailer> trailers;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_trailer);
        final String movie_id = getIntent().getStringExtra("movie_id");

        trailers = new ArrayList<>();
        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize("AIzaSyBcjShZielz8I3TIwBIIA229ucO9cjywYE",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        final YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        String trailerURL = "https://api.themoviedb.org/3/movie/" + movie_id + "/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
                        client.get(trailerURL, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    trailers.clear();
                                    trailerJsonResults = response.getJSONArray("youtube");
                                    trailers.addAll(Trailer.fromJSONArray(trailerJsonResults));
                                    Log.d("YOUTUBE", String.valueOf(trailers));
                                    for (Trailer t: trailers) {
                                        if (t.getType().equals("Trailer")) {
                                            youTubePlayer.loadVideo(t.getSrc());
                                            break;
                                        }
                                    }

                                } catch (JSONException e) {
                                    Log.d("fail", "Failure ");
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                            }
                        });

                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

    }
}
