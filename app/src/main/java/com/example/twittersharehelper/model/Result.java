package com.example.twittersharehelper.model;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.parser.Parser;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class Result {
    @NonNull
    public final Bundle input;
    @NonNull
    public final Parser parser;
    @NonNull
    public final List<Textable> output;

    public Result(@NonNull Bundle input, @NonNull Parser parser, @NonNull List<Textable> output) {
        this.input = input;
        this.parser = parser;
        this.output = output;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringJoiner("\n")
                .add("Bundle")
                .add(convertBundleToString(input))
                .add("")
                .add("Parser")
                .add(parser.getClass().getCanonicalName())
                .add("")
                .add("Candidate")
                .add(convertListToString(output))
                .toString();
    }

    @NonNull
    private String convertBundleToString(@NonNull Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        StringJoiner joiner = new StringJoiner("\n");
        for (String key : keySet) {
            joiner.add(key + " : " + bundle.get(key));
        }
        return joiner.toString();
    }

    @NonNull
    private <T> String convertListToString(@NonNull List<Textable> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < list.size(); i++) {
            joiner.add(i + " : " + list.get(i).toText());
        }
        return joiner.toString();
    }
}
