package com.example.twittersharehelper.model.parser;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Convertible;

import java.util.List;

public interface Parsable<Output extends Convertible> {
    @NonNull
    List<Output> parse(@NonNull Bundle source);

    boolean canParse(@NonNull Bundle source);
}
