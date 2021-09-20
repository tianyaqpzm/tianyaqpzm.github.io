package com.pei.learn.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    //    禁止将系统内部使用对锁对象 暴露外部， 防御方法： 使用私有锁对象
//    攻击者可通过获取可访问对象对隐式锁 并无限期持有 触发条件竞争与死锁,引起拒绝服务DOS
    private final Object lock = new Object();

    //    禁止 基于可被重用对对象进行同步

    /**
     * error
     * reason: java虚拟机共享同一个Boolean类，Boolean不适合作为锁
     */
    private final Boolean init = Boolean.FALSE;

    /**
     * error
     * reason: 封包会使用整形范围对同一个实例，当其他使用integer lock =0,会共用同一个锁导致死锁
     * 基于封包对数值对象进行同步都是不安全的
     */
    private int count = 0;
    private final Integer lockInteger = count;

    /**
     * right
     * 使用new 的Integer 对象具有唯一引用
     */
    private final Integer lockInteger1 = new Integer(count);


    /**
     * error
     * intern 如果字符串常量池存在相同的String ,会返回该字符串的地址
     */
    private final String lockString = new String("LOCK").intern();

    /**
     * error
     * 字符串常量会自动调用 intern
     */
    private final String lockString2 = "LOCK";


    /**
     * right
     */
    private final String lockString3 = new String("LOCK");

    /**
     * right
     */
    private final Object lockObject4 = new Object();


//    禁止使用基于getClass()返回的类对象进行同步
//    使用 Class.forName() 来基于Base类进行同步，=== Base.class

    /**
     * errot
     * 禁止使用基于高层并发对象的内置锁进行同步
     * 实现concurrent.locks 和Condition接口的类，被认为时高层并发对象，实际使用两个不同的锁，
     */
    private final Lock rtLock = new ReentrantLock();


    private void doSomething() {
        rtLock.lock();
        try {

        } finally {
            rtLock.unlock();
        }
    }

    //    5.5 禁止使用一个实例锁 同步共享静态数据
//     通过静态对象实例保证静态属性counter的同步
    private static volatile int counter;
    //    private final Object lock1 = new Object();
    private static final Object lock1 = new Object();


}
