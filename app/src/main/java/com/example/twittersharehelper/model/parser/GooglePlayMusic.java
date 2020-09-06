package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Song;

import java.util.List;

public class GooglePlayMusic extends TextPlainParser<Song> {
    private static final String SPLIT_WORD = "の";
    private static final String SUFFIX = "をチェック";
    private static final GooglePlayMusic INSTANCE = new GooglePlayMusic();

    private GooglePlayMusic() {
    }

    public static GooglePlayMusic getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Song> parse(@NonNull String subject, @NonNull String text) {
        subject = ParseUtils.replaceEmpty(subject, SUFFIX);
        return ParseUtils.getCandidatesByDivide(subject, text, SPLIT_WORD);
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("play.google.com");
    }
}
