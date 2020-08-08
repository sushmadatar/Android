package com.sushma.imagesearch.Event;

public class MessageEvent {
    int eventType;
    Object eventObject;
    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Object getEventObject() {
        return eventObject;
    }

    public void setEventObject(Object eventObject) {
        this.eventObject = eventObject;
    }

    public MessageEvent(int eventType, Object eventObject) {
        this.eventType = eventType;
        this.eventObject = eventObject;
    }



}
