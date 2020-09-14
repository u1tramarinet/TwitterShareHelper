package com.example.twittersharehelper.model.parser;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTube extends TextPlainParser<Media> {
    private static final String SPLIT_WORD = ": ";
    private static final Affix AFFIX_YOUTUBE = new Affix("\"", "\" を YouTube で見る");
    private static final YouTube INSTANCE = new YouTube();

    protected YouTube() {
    }

    @NonNull
    public static YouTube getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean match(@NonNull String text) {
        return text.contains("youtu.be") && match(text, AFFIX_YOUTUBE.prefix, AFFIX_YOUTUBE.suffix);
    }

    @NonNull
    protected List<Media> parse(@NonNull String subject, @NonNull String text) {
        Log.d(YouTube.class.getSimpleName(), "parse() subject=" + subject + ", text=" + text);
        List<Media> mediaList = new ArrayList<>();
        mediaList.add(parseMedia(subject, text, AFFIX_YOUTUBE));
        return mediaList;
    }

    @NonNull
    protected Media parseMedia(@NonNull String subject, @NonNull String text, @NonNull Affix affix) {
        return parseMedia(subject, text, affix.prefix, affix.suffix);
    }

    @NonNull
    protected Media parseMedia(@NonNull String subject, @NonNull String text, @NonNull String prefix, @NonNull String suffix) {
        String title = ParseUtils.removeFix(subject, prefix, suffix);
        text = ParseUtils.replaceEmpty(text, title + SPLIT_WORD);
        Media media = new Media();
        media.setTitle(title);
        media.setUrl(text);
        return media;
    }

    protected boolean match(@NonNull String input, @NonNull Affix affix) {
        return match(input, affix.prefix, affix.suffix);
    }

    protected boolean match(@NonNull String input, @NonNull String prefix, @NonNull String suffix) {
        return match(input, prefix) && match(input, suffix);
    }

    private boolean match(@NonNull String input, @NonNull String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
