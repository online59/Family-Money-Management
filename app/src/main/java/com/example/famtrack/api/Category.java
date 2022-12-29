package com.example.famtrack.api;

public class Category {

    private int iconCategory;
    private String tvCategoryName;
    private String categoryUid;

    public Category() {
    }

    public int getIconCategory() {
        return iconCategory;
    }

    public void setIconCategory(int iconCategory) {
        this.iconCategory = iconCategory;
    }

    public String getTvCategoryName() {
        return tvCategoryName;
    }

    public void setTvCategoryName(String tvCategoryName) {
        this.tvCategoryName = tvCategoryName;
    }

    public String getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(String categoryUid) {
        this.categoryUid = categoryUid;
    }
}
