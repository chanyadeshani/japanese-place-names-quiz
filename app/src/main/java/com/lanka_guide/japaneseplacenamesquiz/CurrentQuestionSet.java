package com.lanka_guide.japaneseplacenamesquiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by prasad on 11/25/17.
 */

public class CurrentQuestionSet {
    private List<PlaceName> placeNames;
    private List<String> japaneseNames;
    private List<String> englishNames;
    private List<String> hiraganaNames;

    public CurrentQuestionSet(List<PlaceName> questionSet) {
        placeNames = questionSet;
        japaneseNames = new ArrayList<>();
        englishNames = new ArrayList<>();
        hiraganaNames = new ArrayList<>();

        for (PlaceName placeName : questionSet) {
            japaneseNames.add(placeName.getKanji());
            englishNames.add(placeName.getEnglish());
            hiraganaNames.add(placeName.getHiragana());
        }

    }


    public List<String> getJapaneseAnswers(PlaceName placeName) {
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

    public List<String> getEnglishAnswers(PlaceName placeName) {
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

    public List<String> getHiraganaAnswers(PlaceName placeName) {
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

    public List<PlaceName> getPlaceNames() {
        List<PlaceName> placeNamesTemp = new ArrayList<>();
        placeNamesTemp.addAll(placeNames);
        Collections.shuffle(placeNamesTemp);
        return placeNamesTemp;
    }

    public PlaceName getAnswer(String name) {
        for (PlaceName placeName : placeNames) {
            if (placeName.getKanji().equals(name)) {
                return placeName;
            } else if (placeName.getEnglish().equals(name)) {
                return placeName;
            }
        }
        return null;
    }

}