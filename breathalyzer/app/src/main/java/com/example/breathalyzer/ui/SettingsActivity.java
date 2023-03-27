package com.example.breathalyzer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.breathalyzer.R;


public class SettingsActivity extends AppCompatActivity {

    private ImageView waterSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        waterSettings = findViewById(R.id.waterSettings);
        waterSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, WaterActivity.class);
            startActivity(intent);
        });
    }
}