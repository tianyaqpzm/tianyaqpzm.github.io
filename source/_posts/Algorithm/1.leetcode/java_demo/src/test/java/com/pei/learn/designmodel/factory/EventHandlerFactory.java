package com.pei.learn.designmodel.factory;


import com.tngtech.archunit.thirdparty.com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class EventHandlerFactory {

    private Map<EventType, EventHandler> handlerMap = new HashMap<>();

    //    @Autowired
    private List<EventHandler> handlerList;

    //    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(handlerList)) {
            handlerList = new ArrayList<>();
            return;
        }
        handlerMap = Maps.uniqueIndex(handlerList, EventHandler::getEventType);
    }

    public EventHandler getEventHandler(EventType eventType) {
        return Optional.ofNullable(handlerMap.get(eventType))
                .orElseThrow(() -> new IllegalStateException("fail to get handler" + eventType.name()));
    }

}
