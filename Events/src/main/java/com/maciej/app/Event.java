package com.maciej.app;

public class Event {
    String id;
    int duration;
    String type;
    String host;
    boolean alert;

    public Event(String id, int duration, String type, String host, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }

    public Event(String id, int duration, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.alert = alert;
    }

    public String getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public boolean isAlert() {
        return alert;
    }
}
