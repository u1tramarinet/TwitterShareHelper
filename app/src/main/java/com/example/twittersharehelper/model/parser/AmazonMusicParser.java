package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Media;
import com.example.twittersharehelper.model.content.PlainText;

import java.util.ArrayList;
import java.util.List;

/**
 * SUBJECT: Amazon Music内で[ARTIST]の[TITLE]を見る
 * TEXT: Amazon Music内で[ARTIST]の[TITLE]を見る https://music.amazon.co.jp/...
 */
class AmazonMusicParser extends MediaParser {
    private static final Affix AFFIX = new Affix("Amazon Music内で", "を見る");
    private static final String SPLIT_WORD = "の";
    private static final String URL_REGEX = "music.amazon.co.jp";

    @Override
    protected List<Media> parse(@NonNull PlainText source) {
        List<Media> mediaList = new ArrayList<>();
        String url = removeAffix(source.text, new Affix(source.subject + "\n", ""));
        String content = removeAffix(source.subject, AFFIX);
        int index = 0;
        do {
            Media media;
            index = content.indexOf(SPLIT_WORD, index + 1);
            if (index <= 0) {
                media = new Media(content, url);
            } else {
                String creator = content.substring(0, index);
                String title = content.substring(index + SPLIT_WORD.length());
                media = new Media(title, creator, url);
            }
            mediaList.add(media);
        } while (index != -1);
        return mediaList;
    }

    @Override
    public boolean match(@NonNull PlainText source) {
        return source.text.contains(URL_REGEX) && match(source.subject, AFFIX);
    }
}
