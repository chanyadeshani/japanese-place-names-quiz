package com.lanka_guide.japaneseplacenamesquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner questionModeSpinner;
    private Spinner categorySpinner;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new Preferences(this);

        initQustionModesSpinner();
        initCategorySpinner();
    }

    public void initQustionModesSpinner() {
        questionModeSpinner = (Spinner) findViewById(R.id.questionModeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.questionModes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionModeSpinner.setAdapter(adapter);

        questionModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                int selectedItem = questionModeSpinner.getSelectedItemPosition();
                QuestionMode selectedQuestionMode;
                if (selectedItem == 2) {
                    selectedQuestionMode = QuestionMode.ENGLISH_KANJI;
                } else if (selectedItem == 3) {
                    selectedQuestionMode = QuestionMode.KANJI_HIRAGANA;
                } else {
                    selectedQuestionMode = QuestionMode.KANJI_ENGLISH;
                }
                preferences.setQuestionMode(selectedQuestionMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        QuestionMode savedMode = preferences.getQuestionMode();

        if (savedMode != null) {
            if (savedMode == QuestionMode.KANJI_ENGLISH) {
                questionModeSpinner.setSelection(1);
            } else if (savedMode == QuestionMode.ENGLISH_KANJI) {
                questionModeSpinner.setSelection(2);
            } else {
                questionModeSpinner.setSelection(3);
            }
        }
    }

    public void initCategorySpinner() {
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                int selectedItem = categorySpinner.getSelectedItemPosition();
                Category selectedCategory;
                switch (selectedItem) {
                    case 1:
                        selectedCategory = Category.AIRPORTS;
                        break;
                    case 2:
                        selectedCategory = Category.CITIES;
                        break;
                    case 3:
                        selectedCategory = Category.COUNTRIES;
                        break;
                    case 4:
                        selectedCategory = Category.PREFECTURES;
                        break;
                    default:
                        selectedCategory = Category.ALL;
                }
                preferences.setCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        QuestionMode savedMode = preferences.getQuestionMode();

        if (savedMode != null) {
            if (savedMode == QuestionMode.KANJI_ENGLISH) {
                questionModeSpinner.setSelection(1);
            } else if (savedMode == QuestionMode.ENGLISH_KANJI) {
                questionModeSpinner.setSelection(2);
            } else {
                questionModeSpinner.setSelection(3);
            }
        }

        Category savedCategory = preferences.getCategory();

        if (savedCategory != null) {
            switch (savedCategory) {
                case AIRPORTS:
                    categorySpinner.setSelection(1);
                    break;
                case CITIES:
                    categorySpinner.setSelection(2);
                    break;
                case COUNTRIES:
                    categorySpinner.setSelection(3);
                    break;
                case PREFECTURES:
                    categorySpinner.setSelection(4);
                    break;
                default:
                    categorySpinner.setSelection(0);
                    break;
            }
        }
    }

    public void onClickQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public enum QuestionMode {
        KANJI_ENGLISH,
        ENGLISH_KANJI,
        KANJI_HIRAGANA;
    }
}