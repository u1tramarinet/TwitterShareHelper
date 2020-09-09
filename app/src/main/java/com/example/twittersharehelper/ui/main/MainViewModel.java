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

import java.util.List;
import java.util.Set;

public class MainViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<Bundle> bundleData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<List<Textable>> candidateData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> textData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> shareEvent = new MutableLiveData<>();

    @NonNull
    public LiveData<Bundle> getBundle() {
        return bundleData;
    }

    public void setBundle(@NonNull Bundle bundle) {
        bundleData.setValue(bundle);
        Set<String> keySet = bundle.keySet();
        StringBuilder total = new StringBuilder();
        for (String key : keySet) {
            Log.d(MainViewModel.class.getSimpleName(), "key=" + key + ", value=" + bundle.get(key));
            total.append(bundle.get(key));
        }
        Parser parser = Classifier.classify(total.toString());
        List<Textable> list = parser.parse(bundle);
        candidateData.setValue(list);
        String text = ((list.get(0))).toText();
        setText(text);
    }

    @NonNull
    public LiveData<List<Textable>> getCandidate() {
        return candidateData;
    }

    @NonNull
    public LiveData<String> getText() {
        return textData;
    }

    public void setText(@NonNull String text) {
        textData.setValue(text);
    }

    public void requestShare() {
        shareEvent.setValue(textData.getValue());
    }

    public LiveData<String> getShareEvent() {
        return shareEvent;
    }
}
