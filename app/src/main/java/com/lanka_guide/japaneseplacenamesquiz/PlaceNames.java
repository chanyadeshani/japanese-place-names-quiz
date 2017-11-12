package com.lanka_guide.japaneseplacenamesquiz;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * District class
 */

public class PlaceNames {

    private static List<PlaceName> placeNames;
    private static List<String> japaneseNames;
    private static List<String> englishNames;
    private Context context;



    public PlaceNames(Context context) {
        this.context = context;

        placeNames = new ArrayList<>();
        japaneseNames = new ArrayList<>();
        englishNames = new ArrayList<>();

        placeNames.add(new PlaceName("羽田", "Haneda", "はねだ"));
        placeNames.add(new PlaceName("成田", "Narita", "なりた"));
        placeNames.add(new PlaceName("関西", "Kansai", "かんさい"));
        placeNames.add(new PlaceName("福岡", "Fukoka", "ふこか"));

        for (PlaceName placeName : placeNames) {
            japaneseNames.add(placeName.getJapanese());
            englishNames.add(placeName.getEnglish());
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

        if (!answers.contains(placeName.getJapanese())) {
            answers.remove(0);
            answers.add(placeName.getJapanese());
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

    public static PlaceName getAnswer(String name) {
        for (PlaceName placeName: placeNames) {
            if(placeName.getJapanese().equals(name)) {
                return placeName;
            } else if (placeName.getEnglish().equals(name)){
                return placeName;
            }
        }
        return null;
    }

    public static class PlaceName {
        String japanese;
        String english;
        String hiragana;

        public PlaceName(String japanese, String english, String hiragana) {
            this.japanese = japanese;
            this.english = english;
            this.hiragana = hiragana;
        }

        public String getJapanese() {
            return japanese;
        }

        public String getEnglish() {
            return english;
        }

        public String getHiragana() {
            return hiragana;
        }
    }



}




