package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView questionTextView, timerTextView;
    RadioGroup optionsRadioGroup;
    Button nextButton;

    List<QuestionModelClass> questions;
    int currentIndex = 0;
    int score = 0;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionTextView = findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.nextButton);

        questions = getQuestions();
        showQuestion();

        nextButton.setOnClickListener(v -> {
            checkAnswer();
            nextQuestion();
        });
    }

    void showQuestion() {
        if (timer != null) timer.cancel();

        QuestionModelClass q = questions.get(currentIndex);
        questionTextView.setText(q.getQuestion());
        optionsRadioGroup.removeAllViews();

        for (int i = 0; i < q.getOptions().length; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(q.getOptions()[i]);
            rb.setId(i);
            optionsRadioGroup.addView(rb);
        }

        startTimer();
    }

    void checkAnswer() {
        if (currentIndex >= questions.size()) return;
        int selected = optionsRadioGroup.getCheckedRadioButtonId();
        if (selected == questions.get(currentIndex).getCorrectAnswerIndex()) {
            score++;
        }
    }

    void nextQuestion() {
        currentIndex++;
        if (currentIndex < questions.size()) {
            showQuestion();
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    void startTimer() {
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                checkAnswer();
                nextQuestion();
            }
        }.start();
    }

    List<QuestionModelClass> getQuestions() {
        List<QuestionModelClass> list = new ArrayList<>();
        list.add(new QuestionModelClass("Capital of France?", new String[]{"Paris", "Berlin", "Madrid", "London"}, 0));
        list.add(new QuestionModelClass("2 + 2 = ?", new String[]{"3", "4", "5", "6"}, 1));
        list.add(new QuestionModelClass("Sun rises from?", new String[]{"West", "North", "East", "South"}, 2));
        list.add(new QuestionModelClass("Largest planet?", new String[]{"Mars", "Earth", "Jupiter", "Venus"}, 2));
        list.add(new QuestionModelClass("Android developed by?", new String[]{"Microsoft", "Apple", "Google", "Samsung"}, 2));
        return list;

    }
}