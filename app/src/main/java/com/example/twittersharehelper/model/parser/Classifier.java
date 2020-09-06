package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

public class Classifier {
    private static Parser[] PARSERS = {
            GooglePlayMusic.getInstance(),
            YouTubeMusic.getInstance(),
            YouTube.getInstance(),
            AmazonMusic.getInstance(),
            SoundCloud.getInstance()
    };

    private Classifier() {
    }

    @NonNull
    public static Parser classify(@NonNull String text) {
        for (Parser parser : PARSERS) {
            if (parser.match(text)) {
                return parser;
            }
        }
        return DefaultParser.getInstance();
    }
}
