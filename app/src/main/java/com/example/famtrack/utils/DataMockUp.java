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

        for (int itemNum = 0; itemNum < expenseCategoryList.length; itemNum++) {
            category = new Category();
            category.setCategoryUid("categoryId" + itemNum);
            category.setIconCategory(R.drawable.european_union);
            category.setTvCategoryName(expenseCategoryList[itemNum]);
            category.setCategoryId("categoryId" + itemNum);

            categoryList.add(category);
        }

        return categoryList;
    }
}
