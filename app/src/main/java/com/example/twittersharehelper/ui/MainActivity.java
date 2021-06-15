package com.example.twittersharehelper.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.twittersharehelper.R;
import com.example.twittersharehelper.ui.main.MainViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

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

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.shareEvent().observe(this, this::shareToTwitter);
    }

    private void readBundle(@NonNull Intent intent) {
        String action = intent.getAction();
        String type = intent.getType();
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
