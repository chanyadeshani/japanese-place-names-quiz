package com.lanka_guide.japaneseplacenamesquiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaceNames {

    private static List<PlaceName> placeNames;
    private static List<String> japaneseNames;
    private static List<String> englishNames;
    private static List<String> hiraganaNames;



    public PlaceNames(JSONObject placeNamesJSON, Category category) {
        placeNames = new ArrayList<>();
        japaneseNames = new ArrayList<>();
        englishNames = new ArrayList<>();
        hiraganaNames = new ArrayList<>();

        parsePlaceNamesJSON(placeNames, placeNamesJSON, category);

        for (PlaceName placeName : placeNames) {
            japaneseNames.add(placeName.getKanji());
            englishNames.add(placeName.getEnglish());
            hiraganaNames.add(placeName.getHiragana());
        }
    }

    private void parsePlaceNamesJSON(List<PlaceName> placeNames, JSONObject placeNamesJSON, Category selectedCategory) {
        try {
            JSONArray placeNamesArray = placeNamesJSON.getJSONArray("placeNames");
            for (int i = 0; i < placeNamesArray.length(); i++) {

                JSONObject categoryJSON = placeNamesArray.getJSONObject(i);

                Category category = Category.valueOf(categoryJSON.getString("category"));

                if(selectedCategory == Category.ALL || category == selectedCategory) {
                    JSONArray namesArray = categoryJSON.getJSONArray("names");

                    for (int j = 0; j < namesArray.length(); j++) {
                        JSONObject nameJSON = namesArray.getJSONObject(j);
                        PlaceName placeName = new PlaceName(category, nameJSON.getString("kanji"), nameJSON.getString("english"), nameJSON
                                .getString("hiragana"));
                        placeNames.add(placeName);
                    }
                }

                if(category == selectedCategory) {
                    return;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<PlaceName> getPlaceNames() {
        List<PlaceName> placeNamesTemp = new ArrayList<>();
        placeNamesTemp.addAll(placeNames);
        Collections.shuffle(placeNamesTemp);
        return placeNamesTemp;
    }

    public static List<String> getJapaneseAnswers(PlaceName placeName) {
        List<String> allNames = new ArrayList<>();
        allNames.addAll(japaneseNames);
        Collections.shuffle(allNames);

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            answers.add(allNames.get(i));
        }

        if (!answers.contains(placeName.getKanji())) {
            answers.remove(0);
            answers.add(placeName.getKanji());
            Collections.shuffle(answers);
        }
        return answers;
    }

    public static List<String> getEnglishAnswers(PlaceName placeName) {
        List<String> allNames = new ArrayList<>();
        allNames.addAll(englishNames);
        Collections.shuffle(allNames);

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            answers.add(allNames.get(i));
        }

        if (!answers.contains(placeName.getEnglish())) {
            answers.remove(0);
            answers.add(placeName.getEnglish());
            Collections.shuffle(answers);
        }
        return answers;
    }

    public static List<String> getHiraganaAnswers(PlaceName placeName) {
        List<String> allNames = new ArrayList<>();
        allNames.addAll(hiraganaNames);
        Collections.shuffle(allNames);

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            answers.add(allNames.get(i));
        }

        if (!answers.contains(placeName.getHiragana())) {
            answers.remove(0);
            answers.add(placeName.getHiragana());
            Collections.shuffle(answers);
        }
        return answers;
    }

    public static PlaceName getAnswer(String name) {
        for (PlaceName placeName: placeNames) {
            if(placeName.getKanji().equals(name)) {
                return placeName;
            } else if (placeName.getEnglish().equals(name)){
                return placeName;
            }
        }
        return null;
    }

    public static class PlaceName {

        Category category;
        String kanji;
        String english;
        String hiragana;

        public PlaceName(Category category, String kanji, String english, String hiragana) {
            this.category = category;
            this.kanji = kanji;
            this.english = english;
            this.hiragana = hiragana;
        }

        public Category getCategory() {
            return category;
        }

        public String getKanji() {
            return kanji;
        }

        public String getEnglish() {
            return english;
        }

        public String getHiragana() {
            return hiragana;
        }
    }

    public enum Category {
        ALL,
        AIRPORTS,
        CITIES,
        COUNTRIES,
        PREFECTURES;
    }
}




