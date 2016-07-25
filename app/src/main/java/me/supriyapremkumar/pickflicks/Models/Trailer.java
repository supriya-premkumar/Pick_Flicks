package me.supriyapremkumar.pickflicks.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by supriya on 7/20/16.
 */
public class Trailer {
    String name;
    String size;
    String type;
    String src;

    public String getType() {
        return type;
    }

    public String getSrc() {
        return src;
    }

    public Trailer(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.size = jsonObject.getString("size");
        this.type = jsonObject.getString("type");
        this.src = jsonObject.getString("source");
    }

    public static ArrayList<Trailer> fromJSONArray(JSONArray array) {
        ArrayList<Trailer> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Trailer(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}

