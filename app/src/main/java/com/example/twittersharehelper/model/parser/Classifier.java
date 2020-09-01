package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

public class Classifier {
    private Classifier() {
    }

    @NonNull
    public static Parser classify(@NonNull String text) {
        if (contains(text, "play.google.com")) {
            return new GooglePlayMusic();
        } else if (contains(text, "music.youtube.com")) {
            return new YouTubeMusic();
        }
        return new Default();
    }

    private static boolean contains(@NonNull String text, @NonNull String... keys) {
        for (String key : keys) {
            if (text.contains(key)) {
                return true;
            }
        }
        return false;
    }
}
