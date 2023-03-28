package com.example.breathalyzer.model;

import java.util.Locale;

public class CountDownTimer {

    private int hours, minutes, seconds, ms;
    private String timeLeft;
    private float time;

    public float getTime() {
        return time;
    }

    public void setTime(String time) {
        float v = Float.parseFloat(time)*60000;
        //Done so for display purposes
        setHours((int)(v/3600000));
        setMinutes((int) ((v/ 1000) % 3600) / 60); //get remainder from hour, then divide by 60 to get min
        setSeconds((int)(v/ 1000) % 60); //
        this.time = v;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {

        this.timeLeft = timeLeft;
    }

    public void setUpTimer()
    {

        String timeLeft;
        if (hours > 0)
            timeLeft = String.format(Locale.getDefault(), "%d:%02d:%02d",getHours(), getMinutes(), getSeconds());
        else
            timeLeft = String.format(Locale.getDefault(), "%02d:%02d", getMinutes(), getSeconds());
        setTimeLeft(timeLeft);

    }

    public CountDownTimer(){

    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }
}
