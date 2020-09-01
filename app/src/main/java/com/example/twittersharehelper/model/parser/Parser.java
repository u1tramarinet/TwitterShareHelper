package com.example.twittersharehelper.model.parser;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Textable;

import java.util.List;

public interface Parser<T extends Textable> {
    @NonNull
    List<T> parse(@NonNull Bundle source);
}
