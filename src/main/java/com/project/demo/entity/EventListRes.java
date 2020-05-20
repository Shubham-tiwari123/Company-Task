package com.project.demo.entity;

public class EventListRes {

    private int eventID;
    private String eventName;
    private String eventDate;
    private String place;

    public EventListRes() {

    }

    public EventListRes(String eventName, String eventDate, String place, int eventID) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.place = place;
        this.eventID = eventID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
