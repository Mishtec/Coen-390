package com.example.breathalyzer.model;


import java.util.Locale;

public class Timer {

    private int hours, minutes, seconds;
    private String TimeLeftDisplay;
    private long displayTime, remainingTime, endOfTimer, startOfTimer;


    public Timer(long time) {
        this.displayTime = time;
        this.startOfTimer = time;
        this.remainingTime = time;
    }



    public long getStartOfTimer() {
        return startOfTimer;
    }

    public void setStartOfTimer(long startOfTimer) {
        this.startOfTimer = startOfTimer;
    }

    public long getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(long time) {
        //Done so for display purposes
        setHours((int) (time / 3600000));
        setMinutes((int) ((time / 1000) % 3600) / 60); //get remainder from hour, then divide by 60 to get min
        setSeconds((int) (time / 1000) % 60); //
        this.displayTime = (time) * 6000;
    }



    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public long getEndOfTimer() {
        return endOfTimer;
    }

    public void setEndOfTimer(long endOfTimer) {
        this.endOfTimer = endOfTimer;
    }

    public String getTimeLeftDisplay() {
        return TimeLeftDisplay;
    }

    public void setTimeLeftDisplay(String timeLeft) {

        this.TimeLeftDisplay = timeLeft;
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

    public void updateUpTimerDisplay() {

        String timeLeft;
        if (hours > 0)
            timeLeft = String.format(Locale.getDefault(), "%d:%02d:%02d", getHours(), getMinutes(), getSeconds());
        else
            timeLeft = String.format(Locale.getDefault(), "%02d:%02d", getMinutes(), getSeconds());
        setTimeLeftDisplay(timeLeft);

    }

    public void resetTimer(){
        setRemainingTime(getStartOfTimer());
    }

}

