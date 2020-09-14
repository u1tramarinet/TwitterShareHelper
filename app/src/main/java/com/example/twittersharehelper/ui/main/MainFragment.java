package com.example.twittersharehelper.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.twittersharehelper.MainActivity;
import com.example.twittersharehelper.R;
import com.example.twittersharehelper.model.Result;
import com.example.twittersharehelper.model.Textable;
import com.example.twittersharehelper.util.SettingsUtil;

import java.util.List;

public class MainFragment extends Fragment {

    private EditText editText;
    private MainViewModel viewModel;
    private MainAdapter adapter;
    private TextView resultText;
    private ScrollView debugView;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isDeveloperMode = SettingsUtil.isDeveloperMode(requireContext());
        if (debugView != null) {
            debugView.setVisibility((isDeveloperMode) ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        editText = root.findViewById(R.id.edit_text);
        resultText = root.findViewById(R.id.result);
        debugView = root.findViewById(R.id.debug);
        root.findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                Log.d(MainFragment.class.getSimpleName(), "text=" + text);
                if (!TextUtils.isEmpty(text)) {
                    viewModel.setText(text);
                    viewModel.requestShare();
                }
            }
        });
        adapter = new MainAdapter(requireContext(), R.id.list_view);
        ListView listView = root.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Textable item = (Textable)(parent).getItemAtPosition(position);
                editText.setText(item.toText());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        viewModel = MainActivity.obtainMainViewModel(requireActivity());
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        });
        viewModel.getCandidate().observe(getViewLifecycleOwner(), new Observer<List<Textable>>() {
            @Override
            public void onChanged(List<Textable> candidates) {
                adapter.updateList(candidates);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                resultText.setText(result.toString());
            }
        });
    }
}
