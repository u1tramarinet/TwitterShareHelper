package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Media;
import com.example.twittersharehelper.model.content.PlainText;

import java.util.ArrayList;
import java.util.List;

/**
 * Service is ended...
 */
class GooglePlayMusicParser extends MediaParser {
    private static final String SPLIT_WORD = "の";
    private static final Affix AFFIX = new Affix("", "をチェック");
    private static final String URL_REGEX = "play.google.com";

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
