package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Song;

import java.util.List;

public class AmazonMusic extends TextPlainParser<Song> {
    private static final Affix AFFIX = new Affix("Amazon Music内で", "を見る");
    private static final String SPLIT_WORD = "の";
    private static final AmazonMusic INSTANCE = new AmazonMusic();

    public static AmazonMusic getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Song> parse(@NonNull String subject, @NonNull String text) {
        text = ParseUtils.replaceEmpty(text, subject + "\n");
        subject = ParseUtils.removeFix(subject, AFFIX.prefix, AFFIX.suffix);
        return ParseUtils.getCandidatesByDivide(subject, text, SPLIT_WORD);
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("music.amazon.co.jp");
    }
}
