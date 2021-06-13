package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Media;
import com.example.twittersharehelper.model.content.PlainText;

import java.util.ArrayList;
import java.util.List;

/**
 * SUBJECT: [TITLE] - SoundCloud
 * TEXT: Listen to [TITLE] by [ARTIST] on \n #SoundCloud\n https://soundcloud...
 */
class SoundCloudTextParser extends MediaParser {
    private static final Affix AFFIX = new Affix("Listen to ", "#SoundCloud\n");
    private static final String SPLIT_WORD = " by ";
    private static final String URL_REGEX = "soundcloud";

    public SoundCloudTextParser() {
    }

    @Override
    protected List<Media> parse(@NonNull PlainText source) {
        List<Media> mediaList = new ArrayList<>();
        String[] split = source.text.split(AFFIX.suffix);
        String url = split[1];
        String content = removeAffix(split[0], AFFIX);
        int index = 0;
        do {
            Media media;
            index = content.indexOf(SPLIT_WORD, index + 1);
            if (index <= 0) {
                media = new Media(content, url);
            } else {
                String title = content.substring(0, index);
                String creator = content.substring(index + SPLIT_WORD.length());
                media = new Media(title, creator, url);
            }
            mediaList.add(media);
        } while (index != -1);

        return mediaList;
    }

    @Override
    public boolean match(@NonNull PlainText source) {
        return source.text.contains(URL_REGEX);
    }
}
