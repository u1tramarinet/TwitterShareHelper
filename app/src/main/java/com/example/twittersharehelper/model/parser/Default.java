package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.PlainText;

import java.util.ArrayList;
import java.util.List;

public class Default implements Parser<PlainText> {
    @NonNull
    @Override
    public List<PlainText> parse(@NonNull Bundle source) {
        List<PlainText> texts = new ArrayList<>();
        CharSequence subject = source.getCharSequence(Intent.EXTRA_SUBJECT, "");
        CharSequence text = source.getCharSequence(Intent.EXTRA_TEXT, "");
        PlainText plainText = new PlainText();
        plainText.subject = subject.toString();
        plainText.text = plainText.toString();
        texts.add(plainText);
        return texts;
    }
}
