package com.example.twittersharehelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class MainFragment extends Fragment {

    private EditText editText;
    private Button shareButton;
    private MainViewModel viewModel;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        editText = root.findViewById(R.id.edit_text);
        shareButton = root.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    viewModel.setText(text);
                }
            }
        });
        initViewModel();

        return root;
    }

    private void initViewModel() {
        FragmentActivity parent = getActivity();
        Objects.requireNonNull(parent);
        viewModel = MainActivity.obtainViewMode(parent);

        viewModel.getBundle().observe(this, new Observer<Bundle>() {
            @Override
            public void onChanged(Bundle bundle) {
                editText.setText(parseBundle(bundle));
            }
        });
    }

    private String parseBundle(@NonNull Bundle bundle) {
        CharSequence subject = bundle.getCharSequence(Intent.EXTRA_SUBJECT, "");
        CharSequence text = bundle.getCharSequence(Intent.EXTRA_TEXT, "");
        return subject + " - " + text;

    }
}
