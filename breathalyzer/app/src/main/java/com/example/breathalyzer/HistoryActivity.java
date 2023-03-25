package com.example.breathalyzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.breathalyzer.data.DatabaseHandler;
import com.example.breathalyzer.databinding.ActivityMainBinding;
import com.example.breathalyzer.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> historyArrayList;
    private ArrayAdapter<String> arrayAdapter;
    private Button historyBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");



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

        // Setup Back button
        historyBackButton = findViewById(R.id.history_back_button);
        historyBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

//        historyView = findViewById(R.id.history_textView);
//        DatabaseHandler db = new DatabaseHandler(HistoryActivity.this);
//        List<History> historyList = db.getAllHistory();
//
//        for (History history: historyList){
//            historyView.append(history.getId() + ". BAC " + history.getBac()
//                    + " collected on " + history.getTimeStamp() + "\n");
//        }
    }
}