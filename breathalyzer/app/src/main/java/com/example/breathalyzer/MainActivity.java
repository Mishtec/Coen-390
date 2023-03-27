package com.example.breathalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.breathalyzer.ui.LoadingActivity;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    protected Button readButton;
    private Button historyButton;
    private Switch drinkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        drinkButton = findViewById(R.id.drinkReminder);
        drinkButton.setOnCheckedChangeListener(this);
    }


    //send Alerts through user input String
    public void Alerts(String input)
    {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.drinkReminder:
                if (b)
                    Alerts("Reminder ON");
                else
                    Alerts("Reminder OFF");
                break;
        }

    }

    //If there is anything to set up in the future for app start up
    //For set up device
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        setUI();
//    }

//    private void setUI()
//    {
//        readButton = findViewById(R.id.takeRead);
//        readButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Measuring...", Toast.LENGTH_SHORT).show();
//                launchLoadingPage(); // launchMeasurementPage();
//                // For testing purpose skip loadingPage
//            }
//        });
//    }

//    public void launchLoadingPage()
//    {
//        //launch a new activity MeasurementPage
//        Intent i = new Intent(this, LoadingAcitiviy.class);//
//        startActivity(i);
//    }

//    public void launchResultsPage()
//    {
//        //launch a new activity MeasurementPage
//        Intent i = new Intent(this, ResultsActivity.class);//
//        startActivity(i);
//    }
//
//

}
