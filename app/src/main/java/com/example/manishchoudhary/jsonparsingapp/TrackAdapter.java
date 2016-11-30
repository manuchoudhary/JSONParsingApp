package com.example.manishchoudhary.jsonparsingapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import java.util.List;

/**
 * Created by manish.choudhary on 11/28/2016.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Track> trackItems;
    ImageLoader imageLoader;

    public TrackAdapter(Activity activity, List<Track> feedItems) {
        this.activity = activity;
        this.trackItems = feedItems;
        imageLoader = AppController.getInstance().getImageLoader();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return trackItems.size();
    }

    public class TrackHolder extends ViewHolder {
        TextView trackName, artistName, collectionName;
        com.android.volley.toolbox.NetworkImageView profilePic;
        CardView card;

        public TrackHolder(View v) {
            super(v);
            this.card = (CardView) v.findViewById(R.id.card_view);
            this.trackName = (TextView) v.findViewById(R.id.trackName);
            this.artistName = (TextView) v.findViewById(R.id.artistName);
            this.collectionName = (TextView) v.findViewById(R.id.collectionName);
            this.profilePic = (com.android.volley.toolbox.NetworkImageView) v.findViewById(R.id.profilePic);
        }
    }

    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_card, parent, false);
        return new TrackHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final TrackHolder holder = (TrackHolder) viewHolder;
        if (!TextUtils.isEmpty(trackItems.get(position).getTrackName())) {
            holder.trackName.setText(trackItems.get(position).getTrackName());
            holder.artistName.setText(trackItems.get(position).getArtistName());
            holder.collectionName.setText(trackItems.get(position).getCollectionName());
            holder.profilePic.setImageUrl(trackItems.get(position).getArtworkUrl30(), imageLoader);
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDetails(trackItems.get(position));
                }
            });
        } else {
            holder.trackName.setVisibility(View.GONE);
            holder.artistName.setVisibility(View.GONE);
            holder.collectionName.setVisibility(View.GONE);
        }
    }

    public void showDetails(Track item){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("item", item);
        activity.startActivity(intent);
    }
}
