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

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", duration=" + duration +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", alert=" + alert +
                '}';
    }
}
