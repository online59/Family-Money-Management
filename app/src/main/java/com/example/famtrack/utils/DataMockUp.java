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

        String[] listOfCategory = {"Transportation", "Accommodation", "Lunch", "Breakfast", "Dinner", "Dessert", "Shopping", "Fuel", "Bill"};

        for (int i = 0; i < listOfCategory.length; i++) {
            category = new Category();
            category.setCategoryUid("categoryId" + i);
            category.setIconCategory(R.drawable.european_union);
            category.setTvCategoryName(listOfCategory[i]);

            categoryList.add(category);
        }

        return categoryList;
    }
}
