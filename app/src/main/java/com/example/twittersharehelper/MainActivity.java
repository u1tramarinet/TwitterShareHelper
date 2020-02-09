package com.example.twittersharehelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private static final String TEXT_PLAIN = "text/plain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        readBundle(intent);
    }

    static MainViewModel obtainViewMode(@NonNull FragmentActivity activity) {
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(activity.getApplication());

        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    private void initViewModel() {
        viewModel = obtainViewMode(this);
        viewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                shareToTwitter(s);
            }
        });
        readBundle();
    }

    private void readBundle() {
        Intent intent = getIntent();
        readBundle(intent);
    }

    private void readBundle(@NonNull Intent intent) {
        String type = intent.getType();

        if (TEXT_PLAIN.equals(type)) {
            Bundle extras = intent.getExtras();
            Objects.requireNonNull(extras);
            viewModel.setBundle(extras);
        }
    }

    private void shareToTwitter(String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }
}
