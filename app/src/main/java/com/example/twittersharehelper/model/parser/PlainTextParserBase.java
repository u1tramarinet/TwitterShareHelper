package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Convertible;
import com.example.twittersharehelper.model.content.PlainText;

import java.util.List;

abstract class PlainTextParserBase<Output extends Convertible> implements Parsable<Output> {
    private static final String KEY_SUBJECT = Intent.EXTRA_SUBJECT;
    private static final String KEY_TEXT = Intent.EXTRA_TEXT;

    @NonNull
    @Override
    public List<Output> parse(@NonNull Bundle source) {
        return parse(parseToTextPlain(source));
    }

    @Override
    public boolean canParse(@NonNull Bundle source) {
        return match(parseToTextPlain(source));
    }

    protected abstract List<Output> parse(@NonNull PlainText source);

    protected abstract boolean match(@NonNull PlainText source);

    private PlainText parseToTextPlain(@NonNull Bundle source) {
        String subject = source.getString(KEY_SUBJECT, null);
        String text = source.getString(KEY_TEXT, null);
        return new PlainText(subject, text);
    }
}
