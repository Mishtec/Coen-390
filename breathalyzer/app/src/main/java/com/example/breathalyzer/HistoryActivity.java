package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breathalyzer.data.DatabaseHandler;
import com.example.breathalyzer.databinding.ActivityMainBinding;
import com.example.breathalyzer.model.History;
import com.example.breathalyzer.ui.WaterActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> historyArrayList;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Setup List View
        listView = findViewById(R.id.history_listView);
        historyArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(HistoryActivity.this);
        // ListView implementation
        List<History> historyList = db.getAllHistory();

        for (History history: historyList){
            historyArrayList.add(history.getBac()
                    + " collected on " + history.getTimeStamp());
        }
        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                historyArrayList
        );
        //add to our listview
        listView.setAdapter(arrayAdapter);



//        historyView = findViewById(R.id.history_textView);
//        DatabaseHandler db = new DatabaseHandler(HistoryActivity.this);
//        List<History> historyList = db.getAllHistory();
//
//        for (History history: historyList){
//            historyView.append(history.getId() + ". BAC " + history.getBac()
//                    + " collected on " + history.getTimeStamp() + "\n");
//        }
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