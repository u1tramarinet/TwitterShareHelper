package com.example.twittersharehelper.model.parser;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Media;

import java.util.ArrayList;
import java.util.List;

public class YouTubeMusic extends YouTube {
    private static final String PREFIX_YOUTUBE_MUSIC = "YouTube で「";
    private static final String SUFFIX_YOUTUBE_MUSIC = "」を見ませんか";
    private static final YouTubeMusic INSTANCE = new YouTubeMusic();

    private YouTubeMusic() {
        super();
    }

    public static YouTubeMusic getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("music.youtube.com");
    }

    protected List<Media> parse(@NonNull String subject, @NonNull String text) {
        Log.d(YouTubeMusic.class.getSimpleName(), "parse() subject=" + subject + ", text=" + text);
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(parseMedia(subject, text, PREFIX_YOUTUBE_MUSIC, SUFFIX_YOUTUBE_MUSIC));
        return mediaList;
    }
}
