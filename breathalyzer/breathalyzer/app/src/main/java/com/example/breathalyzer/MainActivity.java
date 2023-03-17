package com.example.breathalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    protected Button readButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");
    }

    //If there is anything to set up in the future for app start up
    //For set up device
    @Override
    protected void onStart()
    {
        super.onStart();
        setUI();
    }

    private void setUI()
    {
        readButton = findViewById(R.id.takeRead);



        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(getApplicationContext(), "Measuring...", Toast.LENGTH_LONG).show();
                launchResultsPage(); // launchMeasurementPage();
                //For testing purpose skip MeasurementPage


            }
        });

    }

    public void launchMeasurementPage()
    {
        //launch a new activity MeasurementPage
        Intent i = new Intent(this, LoadingAcitiviy.class);//
        startActivity(i);
    }

    public void launchResultsPage()
    {
        //launch a new activity MeasurementPage
        Intent i = new Intent(this, ResultsActivity.class);//
        startActivity(i);
    }



    //send Alerts through user input String
    public void Alerts(String input)
    {
        Toast.makeText(this, input, Toast.LENGTH_LONG).show();
    }

}