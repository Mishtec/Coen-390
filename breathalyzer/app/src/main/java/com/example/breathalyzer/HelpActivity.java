package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.breathalyzer.ui.WaterActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        if (id == R.id.water_reminder) {
            Alerts("Opened Setting");
            Intent intent = new Intent(this, WaterActivity.class);
            startActivity(intent);
            return true;
        }
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
}