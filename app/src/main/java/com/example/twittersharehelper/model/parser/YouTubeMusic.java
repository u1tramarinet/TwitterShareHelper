package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Song;

import java.util.ArrayList;
import java.util.List;

public class YouTubeMusic implements Parser<Song> {
    private static final String SPLIT_WORD = ": ";
    private static final String PREFIX_YOUTUBE = "YouTube で「";
    private static final String SUFFIX_YOUTUBE = "」を見ませんか";
    private static final String WAKE_WORD = "music.youtube.com";
    private static final String PREFIX_MUSIC = "\"";
    private static final String SUFFIX_MUSIC = "\" を YouTubeで見る";

    @NonNull
    @Override
    public List<Song> parse(@NonNull Bundle source) {
        List<Song> songs = new ArrayList<>();
        CharSequence artistAndTrack = source.getCharSequence(Intent.EXTRA_SUBJECT, "");
        CharSequence url = source.getCharSequence(Intent.EXTRA_TEXT, "");

        String aat = artistAndTrack.toString();
        boolean isYouTubeMusic =  url.toString().contains(WAKE_WORD);
        String prefix = (isYouTubeMusic) ? PREFIX_MUSIC : PREFIX_YOUTUBE;
        String suffix = (isYouTubeMusic) ? SUFFIX_MUSIC : SUFFIX_YOUTUBE;
        String track = aat.split(prefix)[1].split(suffix)[0];

        String urlStr = url.toString();
        if (!isYouTubeMusic) {
            urlStr = urlStr.split(track + SPLIT_WORD)[1];
        }

        Song song = new Song();
        song.url = urlStr;
        song.track = track;
        songs.add(song);
        return songs;
    }
}
