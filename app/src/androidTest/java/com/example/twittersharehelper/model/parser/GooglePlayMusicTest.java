package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.twittersharehelper.model.Song;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GooglePlayMusicTest {
    @NonNull
    private final GooglePlayMusic parser = new GooglePlayMusic();

    @Test
    public void parse() {
        String artist = "AAA";
        String track = "BBB";
        String subject = artist + "の" + track + "をチェック";
        String url = "ccc";
        String text = url;
        List<Song> songs = parser.parse(setUpBundle(subject, text));
        Assert.assertEquals(2, songs.size());
        assertEquals(songs.get(0), artist, track, url);
        assertEquals(songs.get(1), null, artist + "の" + track, url);
    }

    private void assertEquals(@NonNull Song song, @Nullable String artist, @Nullable String track, @Nullable String url) {
        Assert.assertEquals(artist, song.artist);
        Assert.assertEquals(track, song.title);
        Assert.assertEquals(url, song.url);
    }

    private Bundle setUpBundle(@NonNull String subject, @NonNull String text) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(Intent.EXTRA_SUBJECT, subject);
        bundle.putCharSequence(Intent.EXTRA_TEXT, text);
        return bundle;
    }
}