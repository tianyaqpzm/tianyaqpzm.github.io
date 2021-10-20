package com.pei.learn.designmodel.Observer;

import com.tngtech.archunit.thirdparty.com.google.common.eventbus.EventBus;
import com.tngtech.archunit.thirdparty.com.google.common.eventbus.Subscribe;

import java.util.Date;

public class Observer {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
    }

}

/**
 * 侦听器是一种用@Subscribe注解修饰的方法，用于接收特定事件，该方法接受与发布事件相同类型的对象作为参数
 */
class TradeConsumer {
    public TradeConsumer(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void auditTrade(TradeEvent tradeEvent) {
        System.out.println("Received trade:" + tradeEvent);
    }
}

/**
 * 生产者：生产者负责发出事件，然后将这些事件传递到事件总线，并发送给订阅该事件的所有侦听器。
 */
class TradeProducer {
    private EventBus eventBus;

    public TradeProducer(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void execute(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        System.out.println("post new trade");
        eventBus.post(new TradeEvent());
    }
}


//
class TradeEvent {
    private double amount;
    private Date tradeTime;
    private TradeType tradeType;
    private TradeAccount tradeAccount;

}


enum TradeType {

}

class TradeAccount {

}
