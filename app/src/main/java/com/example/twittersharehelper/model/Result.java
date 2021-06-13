package com.example.twittersharehelper.model;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.Convertible;
import com.example.twittersharehelper.model.parser.Parsable;

import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

public class Result {
    @NonNull
    public final Bundle input;
    @NonNull
    public final Parsable<?> parser;
    @NonNull
    public final List<Convertible> output;

    public Result(@NonNull Bundle input, @NonNull Parsable<?> parser, @NonNull List<Convertible> output) {
        this.input = input;
        this.parser = parser;
        this.output = output;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringJoiner("\n")
                .add("** Bundle **")
                .add(convertBundleToString(input))
                .add("** Parser **")
                .add(parser.getClass().getCanonicalName())
                .add("** Candidate **")
                .add(convertListToString(output))
                .toString();
    }

    @NonNull
    private String convertBundleToString(@NonNull Bundle bundle) {
        StringJoiner joiner = new StringJoiner("\n");
        for (String key : bundle.keySet()) {
            joiner.add(key + " : " + bundle.get(key));
        }
        return joiner.toString();
    }

    @NonNull
    private <T> String convertListToString(@NonNull List<Convertible> list) {
        String format = "[%d] %s";
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < list.size(); i++) {
            joiner.add(String.format(Locale.JAPAN, format, i, list.get(i).convert()));
        }
        return joiner.toString();
    }
}
