package com.project.demo.entity;

public class EventListReq {

    private String eventName;
    private String eventDate;
    private String place;

    public EventListReq() {

    }

    public EventListReq(String eventName, String eventDate, String place) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.place = place;
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
