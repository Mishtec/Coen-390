package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.breathalyzer.ui.LoadingActivity;
import com.example.breathalyzer.ui.OnBoardFirst;

public class MainActivity extends AppCompatActivity {
    HelpActivity.WaterActivity waterActivity = new HelpActivity.WaterActivity();

    // used for how to guide pop up window
    private ImageButton howToButton;

    private ImageButton readButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup TakeRead button
        readButton = findViewById(R.id.blow_button);
        readButton.setOnClickListener(view -> {
            Alerts("Measuring...");
            Intent i = new Intent(this, LoadingActivity.class);
            startActivity(i); // launchMeasurementPage();
        });

        //Setup "how to guide" button
        howToButton = findViewById(R.id.how_to_button);
        howToButton.setOnClickListener(view -> {
            Intent i = new Intent(this, OnBoardFirst.class);
            startActivity(i);
        });
    }

//    Press Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
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

    //send Alerts through user input String
    public void Alerts(String input)
    {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
//NEXT TWO FUNCTIONS SHOULD BE MOVED TO WATER REMINDER
   /* @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        switch (compoundButton.getId()){
            case R.id.drinkReminderSwitch:
                if (b){
                   editor.putBoolean("reminder", true);
                    editor.apply();
                    if(!prefs.getBoolean("timerRunning", false))
                    {
                        Intent intent = new Intent(this, WaterActivity.class);
                        startActivity(intent);
                        Alerts("Start Reminder ON");

                    }
                }
                else{
                    editor.putBoolean("reminder", false);
                    Alerts("Reminder OFF");
                    editor.apply();
                }

                break;
        }

    }

    @Override
    protected void onStart() { //move to water activity

        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        if (prefs.getBoolean("reminder", false))
        {
            drinkReminderSwitch.setChecked(true);
        }

        else
            drinkReminderSwitch.setChecked(false);
    } */
}
