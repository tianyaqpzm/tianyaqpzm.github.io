package com.pei.learn.designmodel.factory;

public interface EventHandler {
    EventType getEventType();

    void handle(EventContent event);
}


