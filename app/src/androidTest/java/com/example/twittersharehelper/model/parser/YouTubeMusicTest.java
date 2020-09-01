package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.twittersharehelper.model.Song;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class YouTubeMusicTest {
    private final YouTubeMusic parser = new YouTubeMusic();
    @Test
    public void parse() {
        String artist = null;
        String track = "BBB";
        String subject = "YouTube で「" + track + "」を見ませんか";
        String url = "ccc";
        String text = track + ": " + url;
        List<Song> songs = parser.parse(setUpBundle(subject, text));
        Assert.assertEquals(1, songs.size());
        assertEquals(songs.get(0), artist, track, url);
    }

    @Test
    public void parse2() {
        String artist = null;
        String track = "BBB";
        String subject = "\"" + track + "\" を YouTubeで見る";
        String url = "https://music.youtube.com/";
        String text = url;
        List<Song> songs = parser.parse(setUpBundle(subject, text));
        Assert.assertEquals(1, songs.size());
        assertEquals(songs.get(0), artist, track, url);
    }

    private void assertEquals(@NonNull Song song, @Nullable String artist, @Nullable String track, @Nullable String url) {
        Assert.assertEquals(artist, song.artist);
        Assert.assertEquals(track, song.track);
        Assert.assertEquals(url, song.url);
    }

    private Bundle setUpBundle(@NonNull String subject, @NonNull String text) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(Intent.EXTRA_SUBJECT, subject);
        bundle.putCharSequence(Intent.EXTRA_TEXT, text);
        return bundle;
    }
}