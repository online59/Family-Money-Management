package com.example.famtrack.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.famtrack.R;
import com.example.famtrack.api.Category;
import com.example.famtrack.utils.DataMockUp;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private final List<Category> categoryList;
    private OnItemClickListener onItemClickListener;

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
        void onItemClick(View view, int position);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tv_category);

            tvCategoryName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        public TextView getTvCategoryName() {
            return tvCategoryName;
        }
    }
}
