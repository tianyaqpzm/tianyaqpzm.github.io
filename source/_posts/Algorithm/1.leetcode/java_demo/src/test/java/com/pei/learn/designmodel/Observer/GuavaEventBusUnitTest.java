package com.pei.learn.designmodel.Observer;

import com.tngtech.archunit.thirdparty.com.google.common.eventbus.EventBus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GuavaEventBusUnitTest {

    private TradeConsumer listener;
    private EventBus eventBus;

    @Before
    public void setUp() {
        eventBus = new EventBus();
        listener = new TradeConsumer(eventBus);
    }

    @After
    public void tearDown() {
        eventBus.unregister(listener);
    }

    @Test
    public void paymentSuccessfulEvent_whenEventHandled_thenSuccess() {
//        listener.resetEventsHandled();

        TradeProducer paymentEvent = new TradeProducer(eventBus);
        paymentEvent.execute(null, 50, null);

    }

}
