package com.example.twittersharehelper.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Media implements Textable {
    @Nullable
    public String title;
    @Nullable
    public String url;

    public Media() {
        this(null, null);
    }

    public Media(@Nullable String title, @Nullable String url) {
        this.title = title;
        this.url = url;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toText() {
        return title + "\n" + url;
    }
}
