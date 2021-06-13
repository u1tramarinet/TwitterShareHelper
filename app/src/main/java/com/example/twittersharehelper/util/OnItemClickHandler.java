package com.example.twittersharehelper.util;

import androidx.annotation.NonNull;

public interface OnItemClickHandler<T> {
    void onItemClick(@NonNull T item);
}
