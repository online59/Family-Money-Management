package com.example.famtrack.view;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDetailsLookup extends ItemDetailsLookup<Long>{

    private final RecyclerView recyclerView;

    public MyItemDetailsLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent event) {
        View view = recyclerView.findChildViewUnder(event.getX(), event.getY());

        if (view != null) {
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
            if (holder instanceof CategoryAdapter.CategoryViewHolder) {
                return ((CategoryAdapter.CategoryViewHolder) holder).getItemDetails();
            }
        }

        return null;
    }
}
