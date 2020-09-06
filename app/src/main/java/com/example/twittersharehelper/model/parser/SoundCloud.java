package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SoundCloud extends TextPlainParser<Song> {
    private static final String SUFFIX = " - SoundCloud";
    private static final String PREFIX_TEXT = "Listen to ";
    private static final String PREFIX_TEXT_2 = " by ";
    private static final String SUFFIX_TEXT = " on #SoundCloud\n";
    private static final SoundCloud INSTANCE = new SoundCloud();

    private SoundCloud() {
    }

    public static SoundCloud getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Song> parse(@NonNull String subject, @NonNull String text) {
        String title = ParseUtils.replaceEmpty(subject, SUFFIX);
        text = ParseUtils.replaceEmpty(text, PREFIX_TEXT, title, PREFIX_TEXT_2);
        String[] texts = text.split(SUFFIX_TEXT);
        Song song = new Song();
        song.title = title;
        song.artist = texts[0];
        song.url = texts[1];
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        return songs;
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("soundcloud.com");
    }
}
