package com.lanka_guide.japaneseplacenamesquiz;

import android.util.SparseArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public enum PlaceNamesDataHolder {

    INSTANCE;

    public static final int QUESTION_SET_SIZE = 10;
    SparseArray<List<PlaceName>> questionSets;
    private Category category;
    private List<PlaceName> placeNames;

    public void init(JSONObject placeNamesJSON, Category category) {
        placeNames = new ArrayList<>();
        questionSets = new SparseArray<>();
        parsePlaceNamesJSON(placeNames, placeNamesJSON, category);
    }

    private void parsePlaceNamesJSON(List<PlaceName> placeNames, JSONObject placeNamesJSON, Category selectedCategory) {
        try {
            int questionSetId = 1;
            int counter = 0;
            List<PlaceName> questionSet = new ArrayList<>();
            JSONArray placeNamesArray = placeNamesJSON.getJSONArray("placeNames");

            for (int i = 0; i < placeNamesArray.length(); i++) {

                JSONObject categoryJSON = placeNamesArray.getJSONObject(i);

                Category category = Category.valueOf(categoryJSON.getString("category"));

                if (selectedCategory == Category.ALL || category == selectedCategory) {
                    JSONArray namesArray = categoryJSON.getJSONArray("names");

                    for (int j = 0; j < namesArray.length(); j++) {
                        JSONObject nameJSON = namesArray.getJSONObject(j);
                        PlaceName placeName = new PlaceName(category, nameJSON.getString("kanji"), nameJSON.getString
                                ("english"), nameJSON
                                .getString("hiragana"), questionSetId);
                        placeNames.add(placeName);
                        questionSet.add(placeName);
                        counter++;

                        if (counter == QUESTION_SET_SIZE) {
                            questionSets.put(questionSetId, questionSet);
                            counter = 0;
                            questionSet.clear();
                            questionSetId++;
                        }
                    }
                }

                // If a category other than Category.ALL is selected and that category is already parsed return.
                if (category == selectedCategory) {
                    break;
                }
            }

            if (questionSet.size() > 0) {
                questionSets.put(questionSetId, questionSet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<PlaceName> getQuestionSet(int questionSetId) {
        return questionSets.get(questionSetId);
    }

}




