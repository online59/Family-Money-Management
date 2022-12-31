package com.example.famtrack.view;

import android.util.Log;
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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final String TAG = "com.example.famtrack.view.CategoryAdapter";
    private final List<Category> categoryList;
    private OnItemClickListener onItemClickListener;
    private int lastPosition = RecyclerView.NO_POSITION; // Storing the last position user clicked on
    private View lastView = null; // Storing the last view user clicked on

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
        }
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(@Nullable String categoryId);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tv_category);

            tvCategoryName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {

                // If the currently selected item's position is not the same as the last position
                if (lastPosition != getAdapterPosition()) {

                    // Set new selected item's position as the last selected position
                    lastPosition = getAdapterPosition();
                    // Activate the current view
                    view.setActivated(true);

                    // If last view is not null
                    if (lastView != null) {

                        // Deactivated last view
                        lastView.setActivated(false);
                    }

                    // Save currently selected view as last selected view
                    lastView = view;
                } else {

                    // Set last position as -1 (For not selecting any item)
                    lastPosition = RecyclerView.NO_POSITION;

                    // Deactivated last view
                    lastView.setActivated(false);

                    // Set last view as null (Because no item is selected)
                    lastView = null;
                }

                if (lastPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(categoryList.get(lastPosition).getCategoryId());
                } else {
                    onItemClickListener.onItemClick(null);
                }
            }
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }
    }
}
