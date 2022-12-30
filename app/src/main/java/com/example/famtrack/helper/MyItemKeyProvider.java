package com.example.famtrack.helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemKeyProvider extends ItemKeyProvider<Long> {

    private final RecyclerView recyclerView;

    public MyItemKeyProvider(RecyclerView recyclerView) {
        // Since this provide is based on stable ids based on whats laid out in the window
        // we can only satisfy "window" scope key access.
        super(SCOPE_CACHED);
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public Long getKey(int position) {
        return recyclerView.getAdapter() == null ? RecyclerView.NO_POSITION : recyclerView.getAdapter().getItemId(position);
    }

    @Override
    public int getPosition(@NonNull Long key) {
        RecyclerView.ViewHolder holder = recyclerView.findViewHolderForItemId(key);
        return holder == null ? RecyclerView.NO_POSITION : holder.getLayoutPosition();
    }
}
