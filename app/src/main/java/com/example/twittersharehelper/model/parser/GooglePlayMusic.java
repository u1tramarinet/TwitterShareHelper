package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Song;

import java.util.ArrayList;
import java.util.List;

public class GooglePlayMusic implements Parser<Song> {
    private static final String SPLIT_WORD = "の";
    private static final String SUFFIX = "をチェック";

    @NonNull
    @Override
    public List<Song> parse(@NonNull Bundle source) {
        List<Song> songs = new ArrayList<>();
        CharSequence artistAndTrack = source.getCharSequence(Intent.EXTRA_SUBJECT, "");
        CharSequence url = source.getCharSequence(Intent.EXTRA_TEXT, "");

        int index = 0;
        String aat = artistAndTrack.toString();
        int suffixIndex = aat.indexOf(SUFFIX);
        int size = (suffixIndex == -1) ? aat.length() : suffixIndex;
        while (index < size) {
            Song song = new Song();
            song.url = url.toString();
            int i = aat.indexOf(SPLIT_WORD, index);

            if (i <= 0) {
                song.track = aat.substring(0, size);
                i = size;
            } else {
                song.artist = aat.substring(0, i);
                song.track = aat.substring(i + 1, size);
            }
            songs.add(song);
            index += (i + 1);
        }
        return songs;
    }
}
