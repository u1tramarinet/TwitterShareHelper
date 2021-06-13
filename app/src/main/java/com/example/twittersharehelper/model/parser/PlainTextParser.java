package com.example.twittersharehelper.model.parser;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.content.PlainText;

import java.util.ArrayList;
import java.util.List;

class PlainTextParser extends PlainTextParserBase<PlainText> {

    @Override
    protected List<PlainText> parse(@NonNull PlainText source) {
        List<PlainText> texts = new ArrayList<>();
        texts.add(source);
        return texts;
    }

    @Override
    protected boolean match(@NonNull PlainText source) {
        return (!source.subject.isEmpty()) && (!source.text.isEmpty());
    }
}
