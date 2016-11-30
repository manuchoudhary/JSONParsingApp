package com.example.manishchoudhary.jsonparsingapp;

import android.app.Activity;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by manish.choudhary on 11/29/2016.
 */

public class DetailActivity extends Activity {

    TextView txtArtistName, txtCollectionName, txtTrackName, txtCollectionPrice, txtTrackPrice, txtReleaseDate;
    Track item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        txtArtistName = (TextView)findViewById(R.id.txtArtistName);
        txtCollectionName = (TextView)findViewById(R.id.txtCollectionName);
        txtTrackName = (TextView)findViewById(R.id.txtTrackName);
        txtCollectionPrice = (TextView)findViewById(R.id.txtCollectionPrice);
        txtTrackPrice = (TextView)findViewById(R.id.txtTrackPrice);
        txtReleaseDate = (TextView)findViewById(R.id.txtReleaseDate);
        Intent intent = getIntent();
        populateData(intent);
    }

    public void populateData(Intent item){
        SimpleDateFormat print = new SimpleDateFormat("MMM d, yyyy");
        Track track = (Track)item.getSerializableExtra("item");
        txtTrackName.setText(track.getTrackName());
        txtCollectionName.setText(track.getCollectionName());
        txtArtistName.setText(track.getArtistName());
        txtCollectionPrice.setText("$ " + String.valueOf(track.getCollectionPrice()));
        txtTrackPrice.setText("$ " + String.valueOf(track.getTrackPrice()));
        txtReleaseDate.setText("" + print.format(track.getReleaseDate()));
    }
}
