package com.example.crudapplication.callbacks;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.crudapplication.model.CategoryBody;

import java.util.HashMap;

public class CategorySharedPreference {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Preference";
    public static final String CAT_NAME = "categoryName";
    public static final String CAT_ID = "categoryId";

    public CategorySharedPreference(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void storeCategoryDetails(String categoryId, String categoryName) {
        editor.putString(CAT_NAME, categoryName);
        editor.putString(CAT_ID, categoryId);
        editor.commit();
    }

//    public HashMap<String, String> getUserDetails() {
//        HashMap<String, String> user = new HashMap<String, String>();
//        user.put(CAT_ID, preferences.getString(CAT_ID, ""));
//        user.put(CAT_NAME, preferences.getString(CAT_NAME, ""));
//        return user;
//    }

        public CategoryBody getCategoryDetail() {
            CategoryBody categoryBody = new CategoryBody();
            categoryBody.setCategoryId(preferences.getString(CAT_ID, ""));
            categoryBody.setCategoryName(preferences.getString(CAT_NAME, ""));
            return categoryBody;
        }
}
