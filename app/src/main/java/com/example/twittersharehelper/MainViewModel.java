package com.example.twittersharehelper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Bundle> bundle = new MutableLiveData<>();
    private MutableLiveData<String> text = new MutableLiveData<>();

    @NonNull
    public LiveData<Bundle> getBundle() {
        return bundle;
    }

    public void setBundle(@NonNull Bundle bundle) {
        this.bundle.postValue(bundle);
    }

    @NonNull
    public LiveData<String> getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text.postValue(text);
    }
}
