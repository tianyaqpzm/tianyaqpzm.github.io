package com.pei.learn.basic.reflect;

class Person {
    public String name; // 姓名 公有
    protected String age;   // 年龄 保护
    private String hobby;   // 爱好   私有

    public Person(String name, String age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public Person() {
    }

    public String getHobby() {
        return hobby;
    }

    protected String getAge() {
        return age;
    }
}

class Employee extends Person {
    public static Integer totalNum = 0; // 员工数
    public int empNo;   // 员工编号 公有
    protected String position;  // 职位 保护
    private int salary; // 工资   私有

    public void sayHello() {
        System.out.printf("Hello, 我是 %s, 今年 %s 岁, 爱好是%s, 我目前的工作是%s, 月入%s元\n", name, getAge(), getHobby(), position, salary);
    }

    private void work() {
        System.out.printf("My name is %s, 工作中勿扰.%n", name);
    }

    public Employee(String name, String age, String hobby, int empNo, String position, int salary) {
        super(name, age, hobby);
        this.empNo = empNo;
        this.position = position;
        this.salary = salary;
        Employee.totalNum++;
    }

    public Employee() {
    }
}