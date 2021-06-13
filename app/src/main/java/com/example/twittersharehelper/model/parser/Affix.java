package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Optional;

class Affix {
    @NonNull
    public final String prefix;
    @NonNull
    public final String suffix;

    public Affix(@Nullable String prefix, @Nullable String suffix) {
        this.prefix = Optional.ofNullable(prefix).orElse("");
        this.suffix = Optional.ofNullable(suffix).orElse("");
    }
}
