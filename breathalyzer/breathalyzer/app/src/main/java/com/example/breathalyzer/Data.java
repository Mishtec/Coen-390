package com.example.breathalyzer;

public class Data extends MainActivity{

    private float bac;//blood alcohol percentage


    private float brac;//ppm


    //For Data History
    private int year;

    private int month;

    private int day;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public Data() {
        bac = 1;


    }

    // created getter and setter methods
    // for all our variables.
    public  float getBac() {
        return bac;
    }

    public void setBac(float level) {
        bac = level;
    }

    public float getBrac(float bac) {
        return brac;
    }

    public void setBrac(float level1) {
        this.brac = level1;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int Nyear) {
        this.year = Nyear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int Nmonth) {
        this.month = Nmonth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int Nday) {
        this.day = Nday;
    }
}
