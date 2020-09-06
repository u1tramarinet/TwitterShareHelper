package com.example.twittersharehelper.model.parser;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.twittersharehelper.model.Song;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

    private ParseUtils() {
    }

    static String removeFix(@NonNull String input, @NonNull String prefix, @NonNull String suffix) {
        return replaceEmpty(input, prefix, suffix);
    }

    static String replaceEmpty(@NonNull String input, @NonNull String... targets) {
        for (String target : targets) {
            input = input.replaceFirst(target, "");
        }
        return input;
    }

    static List<Song> getCandidatesByDivide(@NonNull String subject, @NonNull String url, @NonNull String splitWord) {
        List<Song> songs = new ArrayList<>();

        int index = 0;

        do {
            Song song = new Song();
            song.url = url;
            index = subject.indexOf(splitWord, index + 1);

            if (index <= 0) {
                song.title = subject;
            } else {
                song.artist = subject.substring(0, index);
                song.title = subject.substring(index + 1);
            }
            songs.add(song);
        } while (index < 0);
        return songs;
    }
}
