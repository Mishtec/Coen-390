package com.example.breathalyzer;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ResultsActivity extends AppCompatActivity {
    //Variable for Firebase
    FirebaseDatabase firebaseDatabase;
    //Variable for Database
    DatabaseReference databaseReference;
    String test, test2;
    private TextView percentAlcohol, ;

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

                test = dataSnapshot.child("bac").getValue(String.class);
               Log.d(TAG, "test is: "+ test);
                percentAlcohol = findViewById(R.id.percentAlcohol);
                percentAlcohol.setText(test);



                //shake hands with each of them

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();


            }
        });


    }
}