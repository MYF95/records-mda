package com.example.springbootrecords.history.model;

import java.util.Date;

public class CalendarEvent {
    private String title;
    private String start;

    public CalendarEvent(String title, Date date) {
        this.title = title;
        this.start = date.toString();
    }

    public String toString(){
        return "{ \"title\": \"" + this.title +
                "\", \"start\": \"" + this.start +
                "\"}";
    }
}
