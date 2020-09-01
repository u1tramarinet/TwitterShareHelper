package com.example.twittersharehelper.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twittersharehelper.model.Textable;
import com.example.twittersharehelper.model.parser.Classifier;
import com.example.twittersharehelper.model.parser.Parser;

import java.util.Set;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Bundle> bundleData = new MutableLiveData<>();
    private MutableLiveData<String> textData = new MutableLiveData<>();
    private MutableLiveData<String> shareEvent = new MutableLiveData<>();

    @NonNull
    public LiveData<Bundle> getBundle() {
        return bundleData;
    }

    public void setBundle(@NonNull Bundle bundle) {
        bundleData.postValue(bundle);
        Set<String> keySet = bundle.keySet();
        StringBuilder total = new StringBuilder();
        for (String key : keySet) {
            Log.d(MainViewModel.class.getSimpleName(), "key=" + key + ", value=" + bundle.get(key));
            total.append(bundle.get(key));
        }
        Parser parser = Classifier.classify(total.toString());
        String text = ((Textable)(parser.parse(bundle).get(0))).toText();
        setText(text);
    }

    @NonNull
    public LiveData<String> getText() {
        return textData;
    }

    public void setText(@NonNull String text) {
        textData.postValue(text);
    }

    public void requestShare() {
        shareEvent.postValue(textData.getValue());
    }

    public LiveData<String> getShareEvent() {
        return shareEvent;
    }
}
