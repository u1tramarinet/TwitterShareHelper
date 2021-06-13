package com.example.twittersharehelper.model.content;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Optional;

public class PlainText implements Convertible {
    @NonNull
    public final String subject;
    @NonNull
    public final String text;

    public PlainText() {
        this(null, null);
    }

    public PlainText(@Nullable String subject, @Nullable String text) {
        this.subject = Optional.ofNullable(subject).orElse("");
        this.text = Optional.ofNullable(text).orElse("");
    }

    @NonNull
    @Override
    public String convert() {
        StringBuilder builder = new StringBuilder(subject);
        if (!TextUtils.isEmpty(subject) && !TextUtils.isEmpty(text)) {
            builder.append(" - ");
        }
        return builder.append(text).toString();
    }
}
