package com.example.breathalyzer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.breathalyzer.MainActivity;
import com.example.breathalyzer.R;
import com.example.breathalyzer.model.CountDownTimer;

public class WaterActivity extends AppCompatActivity {
    MainActivity activity;
    CountDownTimer timer;

    private EditText userInput;
    private TextView timeRemaining;
    private Button setMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity = new MainActivity();
        userInput = findViewById(R.id.minutes);
        timeRemaining = findViewById(R.id.timeRemaining);
        setMinutes = findViewById(R.id.setMinutes);


        setMinutes.setOnClickListener(v -> {
            String input = userInput.getText().toString();
            if (input.length() == 0) {
                activity.Alerts("Error...");
                return;
            }
            float timeInSeconds = Float.parseFloat(input)*60;
            if (timeInSeconds == 0){
                activity.Alerts("Error...");
                return;
            }
            //Set timer here
            timer = new CountDownTimer();
            timer.setTime(input);
            timer.setUpTimer();
            timeRemaining.setText(timer.getTimeLeft());


            //Reset Minutes placeholder
            userInput.setText("");
        });
    }
}