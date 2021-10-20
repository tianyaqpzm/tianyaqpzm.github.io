package com.pei.learn.designmodel.factory;


public enum EventType {
    Expire;
}

enum EventType2 {
    Expire("alias");

    private String name;

    EventType2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
