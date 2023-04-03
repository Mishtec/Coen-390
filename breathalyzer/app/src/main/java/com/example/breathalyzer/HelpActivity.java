package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breathalyzer.model.Timer;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // enable back to home screen button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    public void Alerts(String input)
    {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
    //Menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.water_reminder) {
//            Alerts("Opened Setting");
//            Intent intent = new Intent(this, WaterActivity.class);
//            startActivity(intent);
//            return true;
//        }
        if (id == R.id.help_button) {
            Alerts("Opened Setting");
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.history_button) {
            Alerts("Opened Setting");
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class WaterActivity extends AppCompatActivity {
        Timer timer;
        CountDownTimer countDownTimer;

        private EditText userInput;
        private TextView timeRemaining;
        private Button setMinutes, reset, start;
        private long clock;
        private boolean timerRunning;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_water);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            userInput = findViewById(R.id.minutes);
            timeRemaining = findViewById(R.id.timeRemaining);
            setMinutes = findViewById(R.id.setMinutes);
            reset = findViewById(R.id.reset);
            start = findViewById(R.id.start);


            setMinutes.setOnClickListener(v -> {
                String input = userInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(this, "Empty...", Toast.LENGTH_SHORT).show();

                    return;
                }
                long timeInMilli = Long.parseLong(input)*60000;
                if (timeInMilli == 0){
                    Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();

                    return;
                }
                //Set timer here
                timer = null;
                timer = new Timer(timeInMilli);
                setMinutes.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
                updateUI();

                //Reset Minutes placeholder
                userInput.setText("");
            });

            start.setOnClickListener(v->{

                if(isTimerRunning() == true){
                    setMinutes.setVisibility(View.INVISIBLE);
                    reset.setVisibility(View.VISIBLE);
                    start.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "Reset first", Toast.LENGTH_SHORT).show();

                } else {
                    int visibility = setMinutes.getVisibility();
                    if (visibility == 0) {
                        Toast.makeText(this, "Must Set...", Toast.LENGTH_SHORT).show();
                    } else {
                        startTimer();
                    }
                }

            });

            //RESET TIMER
            reset.setOnClickListener(v ->{
                timer.resetTimer();
                if(isTimerRunning() == true){
                    countDownTimer.cancel();
                    setMinutes.setVisibility(View.VISIBLE);
                    reset.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.VISIBLE);
                    setTimerRunning(false);
                }
                updateUI();


            });
        }

        public void startTimer() {
            clock = System.currentTimeMillis();
            timer.setEndOfTimer(clock + timer.getRemainingTime());

            countDownTimer = new CountDownTimer(timer.getRemainingTime(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timer.setRemainingTime(millisUntilFinished);
                    updateUI();
                }

                @Override
                public void onFinish() {
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

                    boolean  reminder = prefs.getBoolean("reminder", false);
                    if (reminder == true)
                    {
                        countDownTimer.cancel();
                        timer.setRemainingTime(timer.getStartOfTimer());
                        startTimer();


                    }
                    else
                    {
                        countDownTimer.cancel();
                        setTimerRunning(false);
                        setMinutes.setVisibility(View.VISIBLE);
                        reset.setVisibility(View.INVISIBLE);
                        start.setVisibility(View.VISIBLE);
                    }


                }
            }.start();
            setTimerRunning(true);

            }

        @Override
        protected void onStop() {
            super.onStop();

            //Shared Preferences
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putLong("startTimeInMillis", timer.getStartOfTimer());
            editor.putLong("millisLeft",  timer.getDisplayTime());
            editor.putBoolean("timerRunning", isTimerRunning());
            editor.putLong("endTime", timer.getEndOfTimer());

            editor.apply();
            //End Shared Preferences


        }


        @Override
        protected void onStart() {
            super.onStart();
                timer = null;
                timer = new Timer(600000*3);
                //Shared Preferences
                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

                timer.setStartOfTimer(prefs.getLong("startTimeInMillis", 600000*3)); //= prefs.getLong("startTimeInMillis", 600000*3);
                timer.setDisplayTime(prefs.getLong("millisLeft", timer.getStartOfTimer())); //  = prefs.getLong("millisLeft", setStartOfTimer);
                boolean  timerRunning= prefs.getBoolean("timerRunning", false);
                setTimerRunning(timerRunning);

                updateUI();

                if (isTimerRunning()) {
                    timer.setEndOfTimer(prefs.getLong("endTime", 0));
                    timer.setRemainingTime(timer.getEndOfTimer() - System.currentTimeMillis());

                    if (timer.getRemainingTime() < 0) {
                        timer.setRemainingTime(0);
                        setTimerRunning(false);
                        Toast.makeText(this, "Timer Finished", Toast.LENGTH_SHORT).show();
                        updateUI();
                    } else {
                        startTimer();
                    }
                }
            }



        public void updateUI() {
            timer.setDisplayTime(timer.getRemainingTime());
            timer.updateUpTimerDisplay();
            //UI set timer Display
            timeRemaining.setText(timer.getTimeLeftDisplay());
        }
        public boolean isTimerRunning() {
            return timerRunning;
        }

        public void setTimerRunning(boolean timerRunning) {
            this.timerRunning = timerRunning;
        }

    }
}