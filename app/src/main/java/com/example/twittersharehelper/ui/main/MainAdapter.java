package com.example.twittersharehelper.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.twittersharehelper.R;
import com.example.twittersharehelper.model.Textable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainAdapter extends ArrayAdapter<Textable> {
    private List<Textable> list = new ArrayList<>();

    public MainAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void updateList(@NonNull List<Textable> list) {
        this.list = list;
        clear();
        addAll(this.list);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Textable getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        position = getCount() - position - 1;
        Textable item = getItem(position);
        if (item != null) {
            String format = "%d: %s";
            ((TextView)convertView.findViewById(R.id.content)).setText(String.format(Locale.JAPAN, format, position, item.toText()));
        }
        return convertView;
    }
}
