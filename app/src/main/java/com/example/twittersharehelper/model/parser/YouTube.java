package com.example.twittersharehelper.model.parser;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Media;

import java.util.ArrayList;
import java.util.List;

public class YouTube extends TextPlainParser<Media> {
    private static final String SPLIT_WORD = ": ";
    private static final String PREFIX_YOUTUBE = "\"";
    private static final String SUFFIX_YOUTUBE = "\" を YouTube で見る";
    private static final YouTube INSTANCE = new YouTube();

    protected YouTube() {
    }

    public static YouTube getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("youtu.be");
    }

    protected List<Media> parse(@NonNull String subject, @NonNull String text) {
        Log.d(YouTube.class.getSimpleName(), "parse() subject=" + subject + ", text=" + text);
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(parseMedia(subject, text, PREFIX_YOUTUBE, SUFFIX_YOUTUBE));
        return mediaList;
    }

    protected Media parseMedia(@NonNull String subject, @NonNull String text, @NonNull String prefix, @NonNull String suffix) {
        String title = ParseUtils.removeFix(subject, prefix, suffix);
        text = ParseUtils.replaceEmpty(text, title + SPLIT_WORD);
        Media media = new Media();
        media.setTitle(title);
        media.setUrl(text);
        return media;
    }
}
