package com.example.twittersharehelper.model.parser;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Media;

import java.util.ArrayList;
import java.util.List;

public class YouTubeMusic extends YouTube {
    private static final Affix AFFIX_YOUTUBE = new Affix("\"", "\" を YouTube で見る");
    private static final Affix AFFIX_YOUTUBE_MUSIC = new Affix("YouTube で「", "」を見ませんか");
    private static final YouTubeMusic INSTANCE = new YouTubeMusic();

    private YouTubeMusic() {
        super();
    }

    @NonNull
    public static YouTubeMusic getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("music.youtube.com")
                && (match(text, AFFIX_YOUTUBE) || match(text, AFFIX_YOUTUBE_MUSIC));
    }

    @NonNull
    protected List<Media> parse(@NonNull String subject, @NonNull String text) {
        Log.d(YouTubeMusic.class.getSimpleName(), "parse() subject=" + subject + ", text=" + text);
        List<Media> mediaList = new ArrayList<>();
        if (match(subject, AFFIX_YOUTUBE.prefix, AFFIX_YOUTUBE.suffix)) {
            mediaList.add(parseMedia(subject, text, AFFIX_YOUTUBE));
        } else {
            mediaList.add(parseMedia(subject, text, AFFIX_YOUTUBE_MUSIC));
        }
        return mediaList;
    }
}
