package com.example.manishchoudhary.jsonparsingapp;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by manish.choudhary on 11/28/2016.
 */

public class Track implements Serializable {
    private double collectionPrice, trackPrice;
    private Date releaseDate;
    private String artistName, collectionName, trackName, artworkUrl30;

    public Track() {
    }

    public Track(String artworkUrl30, double collectionPrice, double trackPrice, Date releaseDate,
                 String artistName, String collectionName, String trackName) {
        this.artworkUrl30 = artworkUrl30;
        this.collectionPrice = collectionPrice;
        this.trackPrice = trackPrice;
        this.releaseDate = releaseDate;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
    }

    public double getCollectionPrice() {
        return collectionPrice;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public Date getReleaseDate() { return releaseDate; }

    public String getArtworkUrl30() { return artworkUrl30; }

    public void setCollectionPrice(double collectionPrice) { this.collectionPrice = collectionPrice; }

    public void setTrackPrice(double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setArtworkUrl30(String artworkUrl30) { this.artworkUrl30 = artworkUrl30; }
}

