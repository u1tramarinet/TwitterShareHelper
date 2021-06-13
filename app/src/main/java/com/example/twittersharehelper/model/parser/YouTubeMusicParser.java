package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Media;
import com.example.twittersharehelper.model.content.PlainText;

import java.util.ArrayList;
import java.util.List;

/**
 * Similar to YouTube...
 */
class YouTubeMusicParser extends YouTubeParser {
    private static final Affix AFFIX = new Affix("\"", "\" を YouTube で見る");
    private static final String URL_REGEX = "music.youtube.com";

    public YouTubeMusicParser() {
        super();
    }

    @Override
    protected List<Media> parse(@NonNull PlainText source) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(parseMedia(source, AFFIX));
        return mediaList;
    }

    @Override
    public boolean match(@NonNull PlainText source) {
        return source.text.contains(URL_REGEX) && match(source.subject, AFFIX);
    }
}
