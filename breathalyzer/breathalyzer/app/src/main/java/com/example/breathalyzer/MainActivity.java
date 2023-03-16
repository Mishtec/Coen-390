package com.example.breathalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    protected Button takeRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        takeRead = findViewById(R.id.takeRead);

        takeRead.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                launchMeasurementPage();
            }
        });

    }

    public void launchMeasurementPage()
    {
        //launch a new activity MeasurementPage
        Intent i = new Intent(this, MeasurementPage.class);//
        startActivity(i);
    }
}