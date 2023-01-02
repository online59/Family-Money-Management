package com.example.famtrack.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.api.Category;
import com.example.famtrack.utils.DataMockUp;

import java.util.List;
import java.util.Objects;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final String TAG = "com.example.famtrack.view.CategoryAdapter";
    private final List<Category> categoryList;
    private OnItemClickListener onItemClickListener;
    private int lastPosition = RecyclerView.NO_POSITION; // Storing the last position user clicked on
    private View lastView = null; // Storing the last view user clicked on
    private String lastCategoryId = null; // Storing the last category view user clicked on

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
            Category categoryData = categoryList.get(position);
            holder.getTvCategoryName().setText(categoryData.getTvCategoryName());

            // Activated view if current item is the selected item
            if (Objects.equals(categoryData.getCategoryId(), lastCategoryId)) {
                holder.getTvCategoryName().setActivated(true);
                lastPosition = holder.getAdapterPosition(); // Update last position as the recycler view rearrange the position
                lastView = holder.getTvCategoryName(); // Update the last view as the recycler view reuse the view
            } else {
                holder.getTvCategoryName().setActivated(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(@Nullable String categoryId, @Nullable String categoryName);
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

                Log.e(TAG, "onClick: " + view.getId());

                // If the currently selected item's position is not the same as the last position
                if (lastPosition != getAdapterPosition()) {

                    // Set new selected item's position as the last selected position
                    lastPosition = getAdapterPosition();

                    // Set new category id as the last selected item id
                    lastCategoryId = categoryList.get(getAdapterPosition()).getCategoryId();

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

                    // Set the last category id  as null (for not selecting any item)
                    lastCategoryId = null;

                    // Deactivated last view
                    lastView.setActivated(false);

                    // Set last view as null (Because no item is selected)
                    lastView = null;
                }

                if (lastPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(categoryList.get(lastPosition).getCategoryId(), categoryList.get(lastPosition).getTvCategoryName());
                } else {
                    onItemClickListener.onItemClick(null, null);
                }
            }
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }
    }
}
