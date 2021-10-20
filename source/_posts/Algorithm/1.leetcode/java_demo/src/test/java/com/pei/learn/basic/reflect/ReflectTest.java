package com.pei.learn.basic.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1、通过Class.forName获取Employee类的Class对象
        String classPath = "com.pei.learn.basic.reflect.Employee";
        Class<?> employeeClass1 = null;
        try {
            employeeClass1 = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(employeeClass1);
        //2、通过实例对象获取Employee的Class对象
        Employee employee = new Employee();
        Class<?> employeeClass2 = employee.getClass();


        //3、字面常量的方式获取Class对象
        Class<Employee> employeeClass3 = Employee.class;
        System.out.println();
//        int.class == Integer.TYPE;

        /******************调用newInstance()方法******************/
        // ## 调用newInstance()方法
        // 1、Class 对象调用newInstance()方法
        Class<Employee> clazz = Employee.class;
        Employee employee2 = (Employee) clazz.newInstance();

// 2、Constructor 构造器调用newInstance()方法
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class, String.class, int.class, String.class, int.class);

        Employee employee1 = clazz.cast(constructor.newInstance("", "", "", 1, "", 1));

        ReflectTest.constructor();

    }

    public static void constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Employee.class;

        System.out.println("--------------------------------------------");
        //获取带String, int参数的public构造函数
        Constructor cs1 = clazz.getConstructor(String.class, int.class);
        Employee employee1 = (Employee) cs1.newInstance("小张", 20);
        System.out.println(cs1.toGenericString() + ":" + employee1.toString());

        System.out.println("--------------------------------------------");
        //获取带String, String, int参数的private构造函数
        Constructor cs2 = clazz.getDeclaredConstructor(String.class, String.class, int.class);
        cs2.setAccessible(true);
        Employee employee2 = (Employee) cs2.newInstance("小张", "50", 20);
        System.out.println(cs2.toGenericString() + ":" + employee2.toString());
        System.out.println("--------------------------------------------");

        //获取所有构造包含private
        Constructor<?> cons[] = clazz.getConstructors();
        // 查看每个构造方法需要的参数
        for (int i = 0; i < cons.length; i++) {
            //获取构造函数参数类型
            Class<?> clazzs[] = cons[i].getParameterTypes();
            System.out.println("构造函数[" + i + "]:" + cons[i].toString());
            System.out.print("参数类型[" + i + "]:(");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }
    }
}
