package com.example.twittersharehelper.model.parser;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Convertible;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final static Parsable<?>[] CANDIDATES = {
            new GooglePlayMusicParser(),
            new YouTubeMusicParser(),
            new YouTubeParser(),
            new AmazonMusicParser(),
            new SoundCloudTextParser(),
            new PlainTextParser(),
            new DefaultTextParser()
    };

    @NonNull
    public List<ParseResult> parse(@NonNull Bundle source) {
        List<ParseResult> results = new ArrayList<>();
        for (Parsable<?> parsable : CANDIDATES) {
            if (parsable.canParse(source)) {
                parsable.parse(source).forEach(convertible -> results.add(new ParseResult(convertible, parsable)));
            }
        }
        return results;
    }

    public static class ParseResult {
        @NonNull
        public final Convertible value;
        @NonNull
        public final Parsable<?> parser;

        private ParseResult(@NonNull Convertible value, @NonNull Parsable<?> parser) {
            this.value = value;
            this.parser = parser;
        }
    }
}
