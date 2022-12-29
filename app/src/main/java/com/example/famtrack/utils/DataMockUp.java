package com.example.famtrack.utils;

import com.example.famtrack.R;
import com.example.famtrack.api.Category;

import java.util.ArrayList;
import java.util.List;

public class DataMockUp {

    private static final int CATEGORY_COUNT = 16;

    public static List<Category> createCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        Category category;

        String[] expenseCategoryList = {"Transportation", "Accommodation", "Lunch", "Breakfast", "Dinner",
                "Desserts", "Shopping", "Fuel", "Bills", "Education", "Kids", "Pets", "Saving", "Healthcare",
                "Drinks", "Rents", "Troll Way", "Travel", "Clothing", "Utilities", "Groceries", "iTunes",
                "Youtube Music", "Netflix", "Disney+ Hotstar", "Other"};

        for (int i = 0; i < expenseCategoryList.length; i++) {
            category = new Category();
            category.setCategoryUid("categoryId" + i);
            category.setIconCategory(R.drawable.european_union);
            category.setTvCategoryName(expenseCategoryList[i]);

            categoryList.add(category);
        }

        return categoryList;
    }
}
