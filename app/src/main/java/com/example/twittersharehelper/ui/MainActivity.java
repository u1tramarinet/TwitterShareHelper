package com.example.twittersharehelper.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.twittersharehelper.R;
import com.example.twittersharehelper.ui.main.MainViewModel;

import java.util.Objects;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;
    private static final String TEXT_PLAIN = "text/plain";
    private static final String TEXT = "text/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        readBundle(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        readBundle(intent);
    }

    public static MainViewModel obtainMainViewModel(@NonNull FragmentActivity activity) {
        return obtainViewModel(activity, MainViewModel.class);
    }

    private void initViewModel() {
        viewModel = obtainMainViewModel(this);
        viewModel.getShareEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                shareToTwitter(s);
            }
        });
    }

    private void readBundle(@NonNull Intent intent) {
        String action = intent.getAction();
        String type = intent.getType();
        Log.d(MainActivity.class.getSimpleName(), "readBundle(): receive intent[action=" + action + ", type=" + type + "]");

        if (null == type) {
            return;
        }

        if (Intent.ACTION_SEND.equals(action)) {
            if (TEXT_PLAIN.equals(type)) {
                Bundle extras = intent.getExtras();
                Objects.requireNonNull(extras);
                viewModel.setBundle(extras);
            } else if (type.startsWith(TEXT)) {
                /* NOP */
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
            /* NOP */
        }
    }

    private void shareToTwitter(@NonNull String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType(TEXT_PLAIN);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }
}
