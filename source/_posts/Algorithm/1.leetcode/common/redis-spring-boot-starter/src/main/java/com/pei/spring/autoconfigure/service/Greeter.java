package com.pei.spring.autoconfigure.service;

import com.pei.spring.autoconfigure.properties.GreeterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class Greeter {

    @Autowired
    private GreeterProperties greeterProperties;

    private String sayMorning() {
        return greeterProperties.getMorningMsg();
    }

    private String sayAfternoon() {
        return greeterProperties.getAfternoonMsg();
    }

    private String sayEvening() {
        return greeterProperties.getEveningMsg();
    }


    /**
     * 外部调用方法
     *
     * @return
     */
    public String sayHello() {
        String msg;
        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (h < 8) {
            msg = sayMorning();
        } else if (h >= 12 && h <= 16) {
            msg = sayAfternoon();
        } else {
            msg = sayEvening();
        }

        return "[" + greeterProperties.getUserName() + "] " + msg;
    }
}
