package com.pei.learn.designmodel.factory.impl;

import com.pei.learn.designmodel.factory.EventContent;
import com.pei.learn.designmodel.factory.EventHandler;
import com.pei.learn.designmodel.factory.EventType;


//@Component
public class ExpiredHandler implements EventHandler {
    @Override
    public EventType getEventType() {
        return null;
    }

    @Override
    public void handle(EventContent event) {

    }
}
