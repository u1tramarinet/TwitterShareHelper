package com.example.twittersharehelper.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twittersharehelper.model.parser.Parser;
import com.example.twittersharehelper.util.OnItemClickHandler;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class MainViewModel extends ViewModel implements OnItemClickHandler<Parser.ParseResult> {
    @NonNull
    private final MutableLiveData<Bundle> bundleData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> shareTextData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> shareEventData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<List<Parser.ParseResult>> parseResultData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> debugInfoData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> debuggableData = new MutableLiveData<>();
    @NonNull
    private final MediatorLiveData<Boolean> shareButtonEnabledData = new MediatorLiveData<>();

    public MainViewModel() {
        super();
        shareButtonEnabledData.addSource(shareTextData, s -> shareButtonEnabledData.postValue(!s.isEmpty()));
    }

    @NonNull
    public LiveData<String> debugInfo() {
        return debugInfoData;
    }

    public void updateDebuggable(boolean debuggable) {
        debuggableData.setValue(debuggable);
    }

    @NonNull
    public LiveData<Boolean> debuggable() {
        return debuggableData;
    }

    public void setBundle(@NonNull Bundle bundle) {
        bundleData.setValue(bundle);
        List<Parser.ParseResult> parseResults = new Parser().parse(bundle);
        parseResultData.postValue(parseResults);
        updateResult(parseResults.get(0));
    }

    @NonNull
    public MutableLiveData<String> shareText() {
        return shareTextData;
    }

    @NonNull
    public LiveData<String> shareEvent() {
        return shareEventData;
    }

    @NonNull
    public LiveData<List<Parser.ParseResult>> parseResult() {
        return parseResultData;
    }

    @NonNull
    public LiveData<Boolean> shareButtonEnabled() {
        return shareButtonEnabledData;
    }

    public void onShareClick(View view) {
        shareEventData.setValue(shareTextData.getValue());
    }

    @Override
    public void onItemClick(@NonNull Parser.ParseResult item) {
        updateResult(item);
    }

    private void updateResult(@NonNull Parser.ParseResult result) {
        shareTextData.setValue(result.value.convert());
        StringJoiner joiner = new StringJoiner("\n");
        Optional.ofNullable(bundleData.getValue()).ifPresent(bundle -> joiner.add(convertBundleToString(bundle)));
        joiner.add(convertParseResultToString(result));
        debugInfoData.setValue(joiner.toString());
    }

    @NonNull
    private String convertBundleToString(@NonNull Bundle bundle) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("[BUNDLE]");
        for (String key : bundle.keySet()) {
            joiner.add("  key= " + key);
            joiner.add("  value= " + bundle.get(key));
        }
        return joiner.toString();
    }

    @NonNull
    private String convertParseResultToString(@NonNull Parser.ParseResult result) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("[PARSER]");
        joiner.add("  " + result.parser.getClass().getSimpleName());
        joiner.add("[RESULT]");
        joiner.add("  " + result.value.convert());
        return joiner.toString();
    }
}
