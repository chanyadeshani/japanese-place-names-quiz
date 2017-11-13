package com.lanka_guide.japaneseplacenamesquiz;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class Preferences {
    private static final String KEY_QUESTION_MODE = "questionMode";
    private static final String KEY_CATEGORY = "category";
    private static final String PREFER_NAME = "preferencesMain";

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Preferences(Context context) {
        this.context = context;
        setPreferences();
    }

    private void setPreferences() {
        preferences = context.getSharedPreferences(PREFER_NAME, context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void cleanPreferences() {
        editor.clear();
        editor.commit();
    }

    public MainActivity.QuestionMode getQuestionMode() {
        String questionModeStr = preferences.getString(KEY_QUESTION_MODE, null);
        if (questionModeStr != null) {
            return MainActivity.QuestionMode.valueOf(questionModeStr);
        }
        return null;
    }

    public void setQuestionMode(MainActivity.QuestionMode questionQuestionMode) {
        editor.putString(KEY_QUESTION_MODE, questionQuestionMode.toString());
        editor.commit();
    }

    public PlaceNames.Category getCategory() {
        String categoryStr = preferences.getString(KEY_CATEGORY, null);
        if (categoryStr != null) {
            return PlaceNames.Category.valueOf(categoryStr);
        }
        return PlaceNames.Category.ALL;
    }

    public void setCategory(PlaceNames.Category category) {
        editor.putString(KEY_CATEGORY, category.toString());
        editor.commit();
    }

}
