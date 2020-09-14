package com.example.twittersharehelper.model;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.parser.AmazonMusic;
import com.example.twittersharehelper.model.parser.DefaultParser;
import com.example.twittersharehelper.model.parser.GooglePlayMusic;
import com.example.twittersharehelper.model.parser.Parser;
import com.example.twittersharehelper.model.parser.SoundCloud;
import com.example.twittersharehelper.model.parser.YouTube;
import com.example.twittersharehelper.model.parser.YouTubeMusic;

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
