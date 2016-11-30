package com.example.manishchoudhary.jsonparsingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView listView;
    private TrackAdapter listAdapter;
    private List<Track> trackItems;
    private String URL_FEED = "https://itunes.apple.com/search?term=Michael+jackson";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        trackItems = new ArrayList<Track>();
        callURLFeed();
    }

    private void callURLFeed() {

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    parseJsonFeed(response);
                    listAdapter = new TrackAdapter(MainActivity.this, trackItems);
                    listView.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonReq);
    }

    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("results");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Integer len = (Integer)response.get("resultCount");

            for (int i = 0; i < len; i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                Track item = new Track();
                item.setArtworkUrl30(feedObj.getString("artworkUrl30"));
                item.setCollectionPrice(feedObj.getDouble("collectionPrice"));
                item.setTrackPrice(feedObj.getDouble("trackPrice"));
                item.setArtistName(feedObj.getString("artistName"));
                if(feedObj.has("collectionName")){
                    item.setCollectionName(feedObj.getString("collectionName"));
                }else {
                    item.setCollectionName(feedObj.getString("trackName"));
                }
                item.setTrackName(feedObj.getString("trackName"));
                trackItems.add(item);
            }
            Toast.makeText(MainActivity.this, "Json Loaded", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
