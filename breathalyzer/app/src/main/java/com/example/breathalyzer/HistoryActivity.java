package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.breathalyzer.data.DatabaseHandler;
import com.example.breathalyzer.model.History;

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

        // enable back to home screen button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        
    }

}