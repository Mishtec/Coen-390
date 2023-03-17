package com.example.breathalyzer.data;
import com.example.breathalyzer.util.Clock;

public class readingHistory {
    private int id;
    private float bac;
    private String timeStamp;

    public readingHistory() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getBac() {
        return bac;
    }

    public void setBac(float bac) {
        this.bac = bac;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp() {
        timeStamp = Clock.getTimeStamp();
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}