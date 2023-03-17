package com.example.breathalyzer.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.breathalyzer.R;
import com.example.breathalyzer.util.Util;

import java.util.ArrayList;
import java.util.List;
public class DatabaseHandler extends SQLiteOpenHelper {
    public static int ID = 0;
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //Table Creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        // using sql, we create table_name(id, name, phone_number)
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_BAC + " FLOAT,"
                + Util.KEY_TIMESTAMP + " TEXT" + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //Create a table again
        onCreate(db);
    }

    //Add History
    public void addHistory(readingHistory history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_ID, history.getId());
        values.put(Util.KEY_BAC, history.getBac());
        values.put(Util.KEY_TIMESTAMP, history.getTimeStamp());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);
        Log.d("DBHandler", "addContact: " + "item added");
        db.close();
    }

    //Get all History
    public List<readingHistory> getAllHistory() {
        List<readingHistory> historyList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                readingHistory history = new readingHistory();
                history.setId(Integer.parseInt(cursor.getString(0)));
                history.setBac(Float.parseFloat(cursor.getString(1)));
                history.setTimeStamp(cursor.getString(2));

                //add contact objects to our list
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        return historyList;
    }
}