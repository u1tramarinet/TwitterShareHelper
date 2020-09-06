package com.example.twittersharehelper.model;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlainText implements Textable {
    @Nullable
    public String subject;
    @Nullable
    public String text;

    public PlainText() {
        this(null, null);
    }

    public PlainText(@Nullable String subject, @Nullable String text) {
        this.subject = subject;
        this.text = text;
    }

    public PlainText setSubject(@Nullable String subject) {
        this.subject = subject;
        return this;
    }

    public PlainText setText(@Nullable String text) {
        this.text = text;
        return this;
    }

    @NonNull
    @Override
    public String toText() {
        String divider = "";
        if (!TextUtils.isEmpty(subject) && !TextUtils.isEmpty(text)) {
            divider = " - ";
        }
        return subject + divider + text;
    }
}
