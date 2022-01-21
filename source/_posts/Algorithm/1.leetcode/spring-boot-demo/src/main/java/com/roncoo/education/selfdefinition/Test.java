package com.roncoo.education.selfdefinition;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {


//        saveGeneratedCBlibProxyFiles();
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("test.xml");
        MyBeanFactoryPostProcessor bean = ac.getBean(MyBeanFactoryPostProcessor.class);
        System.out.println(bean);
    }
}
