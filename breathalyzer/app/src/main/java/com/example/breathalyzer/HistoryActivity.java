package com.example.breathalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.breathalyzer.data.DatabaseHandler;
import com.example.breathalyzer.data.readingHistory;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private TextView historyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        historyView = findViewById(R.id.history_textView);
        DatabaseHandler db = new DatabaseHandler(HistoryActivity.this);
        List<readingHistory> historyList = db.getAllHistory();

        for (readingHistory history: historyList){
            historyView.append(history.getId() + ". BAC " + history.getBac()
                    + " collected on " + history.getTimeStamp() + "\n");
        }
    }
}