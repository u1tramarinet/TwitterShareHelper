package com.example.twittersharehelper.ui.main;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twittersharehelper.databinding.ListItemBinding;
import com.example.twittersharehelper.model.parser.Parser;
import com.example.twittersharehelper.util.OnItemClickHandler;

public class MainAdapter extends ListAdapter<Parser.ParseResult, MainAdapter.MainViewHolder> {
    @NonNull
    private final LifecycleOwner lifecycleOwner;
    @NonNull
    private final OnItemClickHandler<Parser.ParseResult> handler;

    protected MainAdapter(@NonNull LifecycleOwner lifecycleOwner, @NonNull OnItemClickHandler<Parser.ParseResult> handler) {
        super(new DiffItemCallback());
        this.lifecycleOwner = lifecycleOwner;
        this.handler = handler;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MainViewHolder(ListItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bind(getItem(position), lifecycleOwner, handler, position);
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ListItemBinding binding;

        public MainViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(@NonNull Parser.ParseResult item, @NonNull LifecycleOwner lifecycleOwner, @NonNull OnItemClickHandler<Parser.ParseResult> handler, int position) {
            binding.setLifecycleOwner(lifecycleOwner);
            binding.setItem(item);
            binding.setHandler(handler);
            binding.setPosition(position);
        }
    }

    private static class DiffItemCallback extends DiffUtil.ItemCallback<Parser.ParseResult> {

        @Override
        public boolean areItemsTheSame(@NonNull Parser.ParseResult oldItem, @NonNull Parser.ParseResult newItem) {
            return areValueSame(oldItem, newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Parser.ParseResult oldItem, @NonNull Parser.ParseResult newItem) {
            return areValueSame(oldItem, newItem);
        }

        private boolean areValueSame(@NonNull Parser.ParseResult oldItem, @NonNull Parser.ParseResult newItem) {
            String oldText = oldItem.value.convert();
            String newText = newItem.value.convert();
            return TextUtils.equals(oldText, newText);
        }
    }
}
