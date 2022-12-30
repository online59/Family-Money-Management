package com.example.famtrack.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemKeyProvider extends ItemKeyProvider<Long> {

    private final RecyclerView recyclerView;

    public MyItemKeyProvider(int scope, RecyclerView recyclerView) {
        super(scope);
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
