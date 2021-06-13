package com.example.twittersharehelper.model.parser;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.DefaultText;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class DefaultTextParser implements Parsable<DefaultText> {

    @Override
    @NonNull
    public List<DefaultText> parse(@NonNull Bundle bundle) {
        Set<String> keySet = bundle.keySet();
        List<String> keys = new ArrayList<>();
        for (String key : keySet) {
            Object value = bundle.get(key);
            Optional.ofNullable(value).ifPresent(v -> keys.add(v.toString()));
        }
        List<DefaultText> texts = new ArrayList<>();
        texts.add(new DefaultText(keys));
        return texts;
    }

    @Override
    public boolean canParse(@NonNull Bundle bundle) {
        return true;
    }
}
