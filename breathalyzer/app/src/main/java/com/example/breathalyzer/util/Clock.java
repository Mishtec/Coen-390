package com.example.breathalyzer.util;

import android.text.format.Time;

public class Clock {
    public static String getTimeStamp() {
        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y-%m-%d @ %H:%M:%S");
        return sTime;
    }
}