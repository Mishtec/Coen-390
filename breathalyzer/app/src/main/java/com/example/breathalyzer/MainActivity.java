package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.breathalyzer.ui.LoadingActivity;
import com.example.breathalyzer.ui.SettingsActivity;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Button readButton;
    private Button historyButton;
    private Switch drinkReminderSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   setTitle("Home");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup TakeRead button
        readButton = findViewById(R.id.takeRead);
        readButton.setOnClickListener(view -> {
            Alerts("Measuring...");
            Intent i = new Intent(this, LoadingActivity.class);
            startActivity(i); // launchMeasurementPage();
            // For testing purpose skip loadingPage
        });

        //Setup History button
        historyButton = findViewById(R.id.history_button);
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });

        //Setup Drink Water button
        drinkReminderSwitch = findViewById(R.id.drinkReminderSwitch);
        drinkReminderSwitch.setOnCheckedChangeListener(this);


    }


    //send Alerts through user input String
    public void Alerts(String input)
    {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.drinkReminderSwitch:
                if (b)
                    Alerts("Reminder ON");
                else
                    Alerts("Reminder OFF");
                break;
        }

    }

    //Press Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //Menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Alerts("Opened Setting");
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//        if(id==R.id.){
//            Alerts("");
//           return true;



}
