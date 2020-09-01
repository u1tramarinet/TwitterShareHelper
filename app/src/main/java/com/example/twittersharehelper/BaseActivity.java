package com.example.twittersharehelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BaseActivity extends AppCompatActivity {

    @SuppressWarnings("unchecked")
    protected static <VM extends ViewModel> VM obtainViewModel(@NonNull FragmentActivity activity, @NonNull Class<? extends ViewModel> clazz) {
        return (VM) new ViewModelProvider(activity).get(clazz);
    }
}
