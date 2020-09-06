package com.example.twittersharehelper.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Song extends Media {
    @Nullable
    public String artist;

    public Song() {
        this(null, null, null);
    }

    public Song(@Nullable String title, @Nullable String artist, @Nullable String url) {
        super(title, url);
        this.artist = artist;
    }

    public void setArtist(@Nullable String artist) {
        this.artist = artist;
    }

    @Override
    @NonNull
    public String toText() {
        return title + ((artist != null) ? (" - " + artist) : "") + "\n" + url;
    }
}
