package com.pei.learn.basic.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://zhuanlan.zhihu.com/p/64156910
 */
public class T00_05_BlockQueue {

    /**
     * 下面是一个对阻塞队列进行并发的插入和弹出操作的测试程序，
     * 在这个程序中，会创建2个生产者线程向阻塞队列中插入数字0~19；
     * 同时也会创建2个消费者线程从阻塞队列中弹出20个数字，并打印这些数字。
     * 而且在程序中也统计了整个程序的耗时，会在所有子线程执行完成之后打印出程序的总耗时。
     * <p>
     * 这里我们期望这个测验程序能够以任意顺序输出0~19这20个数字，然后打印出程序的总耗时，
     * <p>
     * <p>
     * JDK在1.5之后引入的代替synchronized关键字的显式锁ReentrantLock类了。ReentrantLock类是一个可重入互斥锁，
     * 我们自然是要把原来的一个条件变量condition给拆分成两个实例变量notFull和notEmpty，这两个条件变量虽然对应于同一互斥锁，
     * 但是两个条件变量的等待和唤醒操作是完全隔离的。这两个条件变量分别代表队列未满和队列非空两个条件，消费者线程因为是被队列为空的情况所阻塞的，
     * 所以就应该等待队列非空条件得到满足；而生产者线程因为是被队列已满的情况所阻塞的，自然就要等待队列未满条件的成立。
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // 创建一个大小为2的阻塞队列
        final BlockingQueue5 q = new BlockingQueue5(2);

        // 创建2个线程
        final int threads = 400;
        // 每个线程执行10次
        final int times = 100;

        // 线程列表，用于等待所有线程完成
        List<Thread> threadList = new ArrayList<>(threads * 2);
        long startTime = System.currentTimeMillis();

        // 创建2个生产者线程，向队列中并发放入数字0到19，每个线程放入10个数字
        for (int i = 0; i < threads; ++i) {
            final int offset = i * times;
            Thread producer = new Thread(() -> {
                try {
                    for (int j = 0; j < times; ++j) {
                        q.put(new Integer(offset + j));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            threadList.add(producer);
            producer.start();
        }
        // 创建2个消费者线程，从队列中弹出20次数字并打印弹出的数字
        for (int i = 0; i < threads; ++i) {
            Thread consumer = new Thread(() -> {
                try {
                    for (int j = 0; j < times; ++j) {
                        Integer element = (Integer) q.take();
                        System.out.println(element);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            threadList.add(consumer);
            consumer.start();
        }

        // 等待所有线程执行完成
        for (Thread thread : threadList) {
            thread.join();
        }

        // 打印运行耗时
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时：%.2fs", (endTime - startTime) / 1e3));

    }
}

class BlockingQueue5 {

    /**
     * 存放元素的数组
     */
    private final Object[] items;

    /**
     * 弹出元素的位置
     */
    private int takeIndex;

    /**
     * 插入元素的位置
     */
    private int putIndex;

    /**
     * 队列中的元素总数
     */
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 弹出锁
     */
    private final ReentrantLock takeLock = new ReentrantLock();

    /**
     * 队列非空的条件变量
     */
    private final Condition notEmpty = takeLock.newCondition();

    /**
     * 插入锁
     */
    private final ReentrantLock putLock = new ReentrantLock();


    /**
     * 队列未满的条件变量
     */
    private final Condition notFull = putLock.newCondition();


    /**
     * 指定队列大小的构造器
     *
     * @param capacity 队列大小
     */
    public BlockingQueue5(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        items = new Object[capacity];
    }

    /**
     * 入队操作
     *
     * @param e 待插入的对象
     */
    private void enqueue(Object e) {
        // 将对象e放入putIndex指向的位置
        items[putIndex] = e;

        // putIndex向后移一位，如果已到末尾则返回队列开头(位置0)
        if (++putIndex == items.length)
            putIndex = 0;

        // 增加元素总数
        count.getAndIncrement();
    }

    /**
     * 出队操作
     *
     * @return 被弹出的元素
     */
    private Object dequeue() {
        // 取出takeIndex指向位置中的元素
        // 并将该位置清空
        Object e = items[takeIndex];
        items[takeIndex] = null;

        // takeIndex向后移一位，如果已到末尾则返回队列开头(位置0)
        if (++takeIndex == items.length)
            takeIndex = 0;

        // 减少元素总数
        count.getAndDecrement();

        // 返回之前代码中取出的元素e
        return e;
    }

    /**
     * 将指定元素插入队列
     *
     * @param e 待插入的对象
     */
    public void put(Object e) throws InterruptedException {
        putLock.lockInterruptibly();
        try {

            // 直到队列未满时才执行入队操作并跳出循环
            while (count.get() == items.length) {
                notFull.await();
            }
            // 执行入队操作，将对象e实际放入队列中
            enqueue(e);
        } finally {
            putLock.unlock();
        }


        // 唤醒等待队列非空条件的线程
        // 为了防止死锁，不能在释放putLock之前获取takeLock
        signalNotEmpty();
    }


    /**
     * 从队列中弹出一个元素
     *
     * @return 被弹出的元素
     */
    public Object take() throws InterruptedException {
        Object e;

        takeLock.lockInterruptibly();
        try {
            // 直到队列非空时才继续执行后续的出队操作并返回弹出的元素
            while (count.get() == 0) {
                notEmpty.await();

            }
            // 执行出队操作，将队列中的第一个元素弹出
            e = dequeue();
        } finally {
            takeLock.unlock();
        }

        // 唤醒等待队列未满条件的线程
        // 为了防止死锁，不能在释放takeLock之前获取putLock
        signalNotFull();
        return e;
    }

    /**
     * 唤醒等待队列非空条件的线程
     */
    private void signalNotEmpty() {
        // 为了唤醒等待队列非空条件的线程，需要先获取对应的takeLock
        takeLock.lock();
        try {
            // 唤醒一个等待非空条件的线程
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * 唤醒等待队列未满条件的线程
     */
    private void signalNotFull() {
        // 为了唤醒等待队列未满条件的线程，需要先获取对应的putLock
        putLock.lock();
        try {
            // 唤醒一个等待队列未满条件的线程
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

}