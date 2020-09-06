package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Textable;

import java.util.List;

public abstract class TextPlainParser<T extends Textable> implements Parser<T> {
    @NonNull
    @Override
    public List<T> parse(@NonNull Bundle source) {
        String subject = source.getCharSequence(Intent.EXTRA_SUBJECT, "").toString();
        String text = source.getCharSequence(Intent.EXTRA_TEXT, "").toString();
        return parse(subject, text);
    }

    @Override
    public boolean match(@NonNull String text) {
        return false;
    }

    protected abstract List<T> parse(@NonNull String subject, @NonNull String text);
}
