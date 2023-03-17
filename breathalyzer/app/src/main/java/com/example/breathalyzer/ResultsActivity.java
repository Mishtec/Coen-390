package com.example.breathalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.breathalyzer.data.DatabaseHandler;
import com.example.breathalyzer.data.readingHistory;
import com.example.breathalyzer.util.Clock;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultsActivity extends AppCompatActivity {
    //Variable for Firebase
    FirebaseDatabase firebaseDatabase;
    //Variable for Database
    DatabaseReference databaseReference;
    private TextView percentAlcohol;
    String bac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //instance of firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        //getting reference for database
        databaseReference = firebaseDatabase.getReference("data");
        //inform anytime data changes on firebase database
        databaseReference.addValueEventListener(new ValueEventListener() {
            //inner class
            @Override
            //This method is invoked anytime data changes on the database
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bac = dataSnapshot.child("bac").getValue(String.class);
                // extracting only the value of bac
                String[] arrOfStr = bac.split(" ", 2);
                bac = arrOfStr[0];

                percentAlcohol = findViewById(R.id.percentAlcohol);
                percentAlcohol.setText(bac);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();


            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        // add last BAC reading to the history
        readingHistory lastData = new readingHistory();
        lastData.setId(++DatabaseHandler.ID);
        lastData.setBac(Float.valueOf(bac));
        lastData.setTimeStamp();
        DatabaseHandler db = new DatabaseHandler(ResultsActivity.this);
        db.addHistory(lastData);
    }
}
