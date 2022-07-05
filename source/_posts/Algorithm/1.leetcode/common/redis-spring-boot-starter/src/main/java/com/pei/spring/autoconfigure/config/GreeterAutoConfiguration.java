package com.pei.spring.autoconfigure.config;

import com.pei.spring.autoconfigure.properties.GreeterProperties;
import com.pei.spring.autoconfigure.service.Greeter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Greeter.class)
@ConditionalOnProperty(prefix = "igoso.greeter", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {


    @Bean("greeter")
    @ConditionalOnMissingBean
    public Greeter greeter() {
        return new Greeter();
    }
}