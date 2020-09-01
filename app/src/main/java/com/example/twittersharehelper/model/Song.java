package com.example.twittersharehelper.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Song implements Textable {
    @Nullable
    public String track;
    @Nullable
    public String artist;
    @Nullable
    public String url;

    public Song() {
        this(null, null, null);
    }

    public Song(@Nullable String track, @Nullable String artist, @Nullable String url) {
        this.track = track;
        this.artist = artist;
        this.url = url;
    }

    public Song setTrack(@Nullable String track) {
        this.track = track;
        return this;
    }

    public Song setArtist(@Nullable String artist) {
        this.artist = artist;
        return this;
    }

    public Song setUrl(@Nullable String url) {
        this.url = url;
        return this;
    }

    @Override
    @NonNull
    public String toText() {
        return track + ((artist != null) ? (" - " + artist) : "") + "\n" + url;
    }
}
