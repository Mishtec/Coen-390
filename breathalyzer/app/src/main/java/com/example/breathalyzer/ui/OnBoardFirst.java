package com.example.breathalyzer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.breathalyzer.MainActivity;
import com.example.breathalyzer.R;

public class OnBoardFirst extends AppCompatActivity {
    private ImageButton closeButton;
    private ImageButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard_first);

        closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(view -> {
            Intent i = new Intent(this, OnBoardSecond.class);
            startActivity(i);
        });
    }
}
