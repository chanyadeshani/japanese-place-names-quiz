package com.lanka_guide.japaneseplacenamesquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner questionModeSpinner;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new Preferences(this);

        initQustionModesSpinner();
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
                    selectedQuestionMode = QuestionMode.ENGLISH;
                } else {
                    selectedQuestionMode = QuestionMode.JAPANESE;
                }
                preferences.setQuestionMode(selectedQuestionMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        QuestionMode savedMode = preferences.getQuestionMode();

        if (savedMode != null) {
            if(savedMode == QuestionMode.JAPANESE) {
                questionModeSpinner.setSelection(1);
            } else if (savedMode == QuestionMode.ENGLISH) {
                questionModeSpinner.setSelection(2);
            }
        }
    }

    public void onClickQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public enum QuestionMode {
        JAPANESE,
        ENGLISH;
    }
}