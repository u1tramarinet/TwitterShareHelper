package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

public class Affix {
    public final String prefix;
    public final String suffix;

    public Affix(@NonNull String prefix, @NonNull String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
