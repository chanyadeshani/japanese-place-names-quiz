package com.lanka_guide.japaneseplacenamesquiz;

/**
 * Created by prasad on 11/25/17.
 */

public class PlaceName {

    Category category;
    String kanji;
    String english;
    String hiragana;
    int questionSetId;

    public PlaceName(Category category, String kanji, String english, String hiragana, int questionSetId) {
        this.category = category;
        this.kanji = kanji;
        this.english = english;
        this.hiragana = hiragana;
        this.questionSetId = questionSetId;
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

    public int getQuestionSetId() {
        return questionSetId;
    }
}