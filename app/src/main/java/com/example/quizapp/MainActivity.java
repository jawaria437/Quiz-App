package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
            Button startQuizButton;

            @Override
                protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                startQuizButton = findViewById(R.id.startQuizButton);
                startQuizButton.setOnClickListener(v -> {
                    startActivity(new Intent(MainActivity.this, QuizActivity.class));
                });
            }
        }
