package com.lanka_guide.japaneseplacenamesquiz;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity {

    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private Button nextButton;
    private Button tryAgainButton;

    private TextView questionText;
    private TextView hiraganaText;
    private TextView timeSpentText;
    private TextView resultText;

    private ColorFilter defaultButtonBackground;

    private Set<String> correctAnswers = new HashSet<>();
    private Set<PlaceNames.PlaceName> displayedQuestions = new HashSet<>();
    private List<PlaceNames.PlaceName> questions = new ArrayList<>();
    private long startTime;
    private int wrongAnswerCount;

    private AdView mAdView;
    private Preferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = new Preferences(this);

        new PlaceNames(getApplicationContext());

        setContentView(R.layout.quiz_activity);

        mAdView = findViewById(R.id.quizAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        questionText = findViewById(R.id.question);

        init();

        defaultButtonBackground = option1.getBackground().getColorFilter();

    }

    public void init() {
        questions = PlaceNames.getPlaceNames();
        correctAnswers.clear();
        displayedQuestions.clear();
        setQuestionAndAnwers();
        wrongAnswerCount = 0;
        startTime = System.currentTimeMillis();
    }

    private void setQuestionAndAnwers() {
        PlaceNames.PlaceName question = questions.get(0);
        questions.remove(0);

        List<String> answers;

        if(preferences.getQuestionMode() == MainActivity.QuestionMode.JAPANESE) {
            questionText.setText(question.getJapanese());
            answers = PlaceNames.getEnglishAnswers(question);
        } else {
            questionText.setText(question.getEnglish());
            answers = PlaceNames.getJapaneseAnswers(question);
        }

        option1 = findViewById(R.id.option1);
        option1.setText(answers.get(0));

        option2 = findViewById(R.id.option2);
        option2.setText(answers.get(1));

        option3 = findViewById(R.id.option3);
        option3.setText(answers.get(2));

        option4 = findViewById(R.id.option4);
        option4.setText(answers.get(3));

        displayedQuestions.add(question);

        showQuestionAndAnswers();
    }

    public void clickAnswer(View view) {
        String currentQuestion = questionText.getText().toString();
        PlaceNames.PlaceName placeName = PlaceNames.getAnswer(currentQuestion);

        String correctAnswer;
        if (placeName.getEnglish().equals(currentQuestion)) {
            correctAnswer = placeName.getJapanese();
        } else {
            correctAnswer = placeName.getEnglish();
        }
        switch (view.getId()) {
            case R.id.option1:
                showAnswer(placeName, correctAnswer, option1);
                break;
            case R.id.option2:
                showAnswer(placeName, correctAnswer, option2);
                break;
            case R.id.option3:
                showAnswer(placeName, correctAnswer, option3);
                break;
            case R.id.option4:
                showAnswer(placeName, correctAnswer, option4);
                break;
        }
    }

    private void showAnswer(PlaceNames.PlaceName placeName, String correctAnswer, Button selectedButton) {
        String selectedAnswer = selectedButton.getText().toString();

        if (selectedAnswer.equals(correctAnswer)) {
            selectedButton.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            disableAllButtons();
            selectedButton.setEnabled(true);
            correctAnswers.add(correctAnswer);
        } else {
            selectedButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            disableAllButtons();

            if (option1.getText().equals(correctAnswer)) {
                option1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                option1.setEnabled(true);
                option1.invalidate();
            } else if (option2.getText().equals(correctAnswer)) {
                option2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                option2.setEnabled(true);
                option2.invalidate();
            } else if (option3.getText().equals(correctAnswer)) {
                option3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                option3.setEnabled(true);
                option3.invalidate();
            } else if (option4.getText().equals(correctAnswer)) {
                option4.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                option4.setEnabled(true);
                option4.invalidate();
            }
            wrongAnswerCount++;
        }
        selectedButton.invalidate();

        hiraganaText = findViewById(R.id.hiragana);
        hiraganaText.setText(placeName.getHiragana());
        hiraganaText.setVisibility(View.VISIBLE);

        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.VISIBLE);

    }

    private void showTimeSpent() {
        timeSpentText = findViewById(R.id.quizTime);
        timeSpentText.setVisibility(View.VISIBLE);
        timeSpentText.bringToFront();

        long timeSpent = System.currentTimeMillis() - startTime;
        String timeSpentStr = getTimeSpenStr(timeSpent);
        timeSpentText.setText(timeSpentStr);
    }

    private String getTimeSpenStr(long timeSpent) {
        return String.format(Locale.ENGLISH, "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeSpent),
                TimeUnit.MILLISECONDS.toSeconds(timeSpent) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeSpent))
        );
    }

    private void disableAllButtons() {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    private void resetAllButtons() {
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        option1.getBackground().setColorFilter(defaultButtonBackground);
        option2.getBackground().setColorFilter(defaultButtonBackground);
        option3.getBackground().setColorFilter(defaultButtonBackground);
        option4.getBackground().setColorFilter(defaultButtonBackground);

        option1.invalidate();
        option2.invalidate();
        option3.invalidate();
        option4.invalidate();

        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);
        hiraganaText.setVisibility(View.GONE);
    }

    private void hideQuestionAndAnswers() {
        option1.setVisibility(View.GONE);
        option2.setVisibility(View.GONE);
        option3.setVisibility(View.GONE);
        option4.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        questionText.setVisibility(View.GONE);
        hiraganaText.setVisibility(View.GONE);
    }

    private void showQuestionAndAnswers() {
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
    }

    public void clickNextQuestion(View view) {
        if (questions.isEmpty() && wrongAnswerCount == 0) {
            resultText = findViewById(R.id.quizResult);
            resultText.setVisibility(View.VISIBLE);
            resultText.setText(R.string.congratz);
            resultText.setTextColor(Color.RED);
            resultText.setTextSize(30);
            resultText.bringToFront();

            hideQuestionAndAnswers();
            showTimeSpent();
        } else if (questions.isEmpty()) {
            resultText = findViewById(R.id.quizResult);
            resultText.setVisibility(View.VISIBLE);
            resultText.bringToFront();

            resultText.setText(String.format(getString(R.string.quizNumOfCorrectAns), correctAnswers.size()));
            resultText.setTextColor(Color.BLACK);
            resultText.setTextSize(15);

            showTimeSpent();
            hideQuestionAndAnswers();

            tryAgainButton = findViewById(R.id.tryAgainButton);
            tryAgainButton.setVisibility(View.VISIBLE);

        } else {
            setQuestionAndAnwers();
            resetAllButtons();
        }
    }

    public void clickTryAgain(View view) {
        init();
        resultText.setVisibility(View.GONE);
        timeSpentText.setVisibility(View.GONE);
        tryAgainButton.setVisibility(View.GONE);
        resetAllButtons();
    }
}