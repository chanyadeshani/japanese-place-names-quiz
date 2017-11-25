package com.lanka_guide.japaneseplacenamesquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String KEY_QUESTION_MODE = "questionMode";
    private static final String KEY_CATEGORY = "category";
    private static final String PREFER_NAME = "preferencesMain";
    private static final String LAST_QUESTION_SET = "lastQuestionSet";

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

    public Category getCategory() {
        String categoryStr = preferences.getString(KEY_CATEGORY, null);
        if (categoryStr != null) {
            return Category.valueOf(categoryStr);
        }
        return Category.ALL;
    }

    public void setCategory(Category category) {
        editor.putString(KEY_CATEGORY, category.toString());
        editor.commit();
    }

    public int getLastQuestionSetId(Category category) {
        return preferences.getInt(LAST_QUESTION_SET + "_" + category.name(), 0);
    }

    public void setLastQuestionSetId(Category category, int id) {
        editor.putInt(LAST_QUESTION_SET + "_" + category.name(), id);
        editor.commit();
    }

}
