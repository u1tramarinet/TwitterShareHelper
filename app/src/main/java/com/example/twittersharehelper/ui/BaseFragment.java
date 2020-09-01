package com.example.twittersharehelper.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BaseFragment extends Fragment {

    @SuppressWarnings("unchecked")
    protected <VM extends ViewModel> VM getViewModelOfParentActivity(@NonNull Class<? extends ViewModel> clazz) {
        return (VM) new ViewModelProvider(requireActivity()).get(clazz);
    }

    @SuppressWarnings("unchecked")
    protected <VM extends ViewModel> VM getViewModel(@NonNull Class<? extends ViewModel> clazz) {
        return (VM) new ViewModelProvider(this).get(clazz);
    }
}
