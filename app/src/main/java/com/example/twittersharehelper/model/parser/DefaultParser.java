package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.PlainText;

import java.util.ArrayList;
import java.util.List;

public class DefaultParser implements Parser<PlainText> {
    private static final DefaultParser INSTANCE = new DefaultParser();

    private DefaultParser() {
    }

    public static DefaultParser getInstance() {
        return INSTANCE;
    }

    @NonNull
    @Override
    public List<PlainText> parse(@NonNull Bundle source) {
        List<PlainText> texts = new ArrayList<>();
        CharSequence subject = source.getCharSequence(Intent.EXTRA_SUBJECT, "");
        CharSequence text = source.getCharSequence(Intent.EXTRA_TEXT, "");
        PlainText plainText = new PlainText();
        plainText.subject = subject.toString();
        plainText.text = text.toString();
        texts.add(plainText);
        return texts;
    }

    @Override
    public boolean match(@NonNull String text) {
        return true;
    }
}
