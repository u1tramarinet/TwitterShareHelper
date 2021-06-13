package com.example.twittersharehelper.model.parser;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Media;
import com.example.twittersharehelper.model.content.PlainText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class MediaParser extends PlainTextParserBase<Media> {
    @NonNull
    protected Media parseMedia(@NonNull PlainText source, @NonNull Affix affix) {
        String title = removeAffix(source.subject, affix);
        return new Media(title, source.text);
    }

    protected boolean match(@NonNull String input, @NonNull Affix affix) {
        return match(input, affix.prefix) && match(input, affix.suffix);
    }

    @NonNull
    protected String removeAffix(@NonNull String input, @NonNull Affix affix) {
        input = removePrefix(input, affix.prefix);
        input = removeSuffix(input, affix.suffix);
        return input;
    }

    private boolean match(@NonNull String input, @NonNull String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    @NonNull
    private String removePrefix(@NonNull String input, @NonNull String prefix) {
        if (TextUtils.isEmpty(prefix)) return input;
        Pattern pattern = Pattern.compile(prefix, Pattern.LITERAL);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceFirst("");
        return input;
    }

    @NonNull
    private String removeSuffix(@NonNull String input, @NonNull String suffix) {
        d("removeSuffix(): input= " + input + ", suffix= " + suffix);
        if (TextUtils.isEmpty(suffix)) return input;
        input = input.substring(0, input.length() - suffix.length());
        d("removeSuffix(): input= " + input);
        return input;
    }

    private void d(String message) {
        Log.d(MediaParser.class.getSimpleName(), message);
    }
}
