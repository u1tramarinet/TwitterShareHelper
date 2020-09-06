package com.example.twittersharehelper.model.parser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.twittersharehelper.model.Media;
import com.example.twittersharehelper.model.Song;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeMusicTest {
    private final YouTubeMusic parser = YouTubeMusic.getInstance();
    @Test
    public void parse() {
        String artist = null;
        String track = "BBB";
        String subject = "YouTube で「" + track + "」を見ませんか";
        String url = "ccc";
        String text = track + ": " + url;
        List<Media> songs = parser.parse(setUpBundle(subject, text));
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
        List<Media> songs = parser.parse(setUpBundle(subject, text));
        Assert.assertEquals(1, songs.size());
        assertEquals(songs.get(0), artist, track, url);
    }

    @Test
    public void match() {
        String input = "YouTube で「ルビコン」を見ませんか";
        String expected = "ルビコン";

        String prefix = "YouTube で「";
        String suffix = "」を見ませんか";
        String format = prefix + ".*" + suffix;
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(input);

        Assert.assertTrue(matcher.find());
        String result = matcher.group();
        Assert.assertEquals(input, result);
        Assert.assertEquals(expected, result.replace(prefix, "").replace(suffix, ""));
    }

    @Test
    public void match2() {
        String input = "\"【クラフトピア/Craftopia】≒OMARUPOLKA【ホロライブ/尾丸ポルカ】\" を YouTube で見る";
        String expected = "【クラフトピア/Craftopia】≒OMARUPOLKA【ホロライブ/尾丸ポルカ】";

        String prefix = "\"";
        String suffix = "\" を YouTube で見る";
        String format = prefix + ".*" + suffix;
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(input);

        Assert.assertTrue(matcher.find());
        String result = matcher.group();
        Assert.assertEquals(input, result);
        Assert.assertEquals(expected, result.replaceFirst(prefix, "").replaceFirst(suffix, ""));
    }

    private void assertEquals(@NonNull Media song, @Nullable String artist, @Nullable String track, @Nullable String url) {
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