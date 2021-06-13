package com.example.twittersharehelper.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twittersharehelper.R;
import com.example.twittersharehelper.databinding.FragmentMainBinding;
import com.example.twittersharehelper.util.SettingsUtil;

public class MainFragment extends Fragment {

    private MainViewModel viewModel;

    public MainFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDebuggable();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        RecyclerView recyclerView = root.findViewById(R.id.list);
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL));
        MainAdapter adapter = new MainAdapter(getViewLifecycleOwner(), viewModel);
        recyclerView.setAdapter(adapter);
        viewModel.getResult().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        updateDebuggable();
    }

    private void updateDebuggable() {
        if (null != viewModel) {
            boolean isDeveloperMode = SettingsUtil.isDeveloperMode(requireContext());
            viewModel.setDebuggable(isDeveloperMode);
        }
    }
}
