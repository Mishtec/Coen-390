package com.example.breathalyzer.model;

public class History {
    private int id;
    private float bac;
    private String timeStamp;

    public History() {
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