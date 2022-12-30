package com.example.famtrack.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.api.Category;
import com.example.famtrack.utils.DataMockUp;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private final List<Category> categoryList;
    private OnItemClickListener onItemClickListener;
    private SelectionTracker<Long> selectionTracker;

    public CategoryAdapter() {
        this.categoryList = DataMockUp.createCategoryList();
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            holder.getTvCategoryName().setText(categoryList.get(position).getTvCategoryName());
//            holder.itemView.setActivated(selectionTracker.isSelected((long) position));
        }
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSelectionTracker(SelectionTracker<Long> selectionTracker) {
        this.selectionTracker = selectionTracker;
    }

    // Set each item in recyclerview to use its position as their id
    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tvCategoryName;

        private int lastPosition = RecyclerView.NO_POSITION;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tv_category);

            tvCategoryName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());

                if (lastPosition != getAdapterPosition()) {
                    lastPosition = getAdapterPosition();
                    view.setActivated(true);
                } else {
                    view.setActivated(false);
                }
            }
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }


        // Provide item details for ItemDetailsLookup class
        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return new ItemDetailsLookup.ItemDetails<Long>() {
                @Override
                public int getPosition() {
                    return getAdapterPosition();
                }

                @Nullable
                @Override
                public Long getSelectionKey() {
                    return getItemId();
                }
            };
        }
    }
}
