package com.example.twittersharehelper.model.content;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class DefaultText implements Convertible {
    @NonNull
    public List<String> texts;

    public DefaultText() {
        this(null);
    }

    public DefaultText(@Nullable List<String> texts) {
        this.texts = Optional.ofNullable(texts).orElse(new ArrayList<>());
    }

    @NonNull
    @Override
    public String convert() {
        StringJoiner joiner = new StringJoiner("\n");
        for (String text : texts) {
            joiner.add(text);
        }
        return joiner.toString();
    }
}
