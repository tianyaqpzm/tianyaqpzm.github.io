package com.roncoo.education.selfdefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        BeanDefinition dataSource = beanFactory.getBeanDefinition("dataSource");
//        dataSource.setDependsOn("person");
        System.out.println("pei MyBeanFactoryPostProcessor");
    }


}
