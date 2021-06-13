package com.example.twittersharehelper.model.content;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Optional;

public class Media implements Convertible {
    @NonNull
    public final String title;
    @NonNull
    public final String creator;
    @NonNull
    public final String url;

    public Media() {
        this(null, null);
    }

    public Media(@Nullable String title, @Nullable String url) {
        this(title, null, url);
    }

    public Media(@Nullable String title, @Nullable String creator, @Nullable String url) {
        this.title = Optional.ofNullable(title).orElse("");
        this.creator = Optional.ofNullable(creator).orElse("");
        this.url = Optional.ofNullable(url).orElse("");
    }

    @NonNull
    @Override
    public String convert() {
        return convert(convert(title, creator, " - "), url, "\n\n");
    }

    @NonNull
    protected String convert(String content, String url, String divider) {
        StringBuilder builder = new StringBuilder(content);
        if (!TextUtils.isEmpty(content) && !TextUtils.isEmpty(url)) {
            builder.append(divider);
        }
        return builder.append(url).toString();
    }
}
