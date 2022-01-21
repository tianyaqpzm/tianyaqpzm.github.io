package com.pei.learn.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * https://zhuanlan.zhihu.com/p/64156753
 */
public class T00_02_BlockQueue {

    /**
     * 下面是一个对阻塞队列进行并发的插入和弹出操作的测试程序，
     * 在这个程序中，会创建2个生产者线程向阻塞队列中插入数字0~19；
     * 同时也会创建2个消费者线程从阻塞队列中弹出20个数字，并打印这些数字。
     * 而且在程序中也统计了整个程序的耗时，会在所有子线程执行完成之后打印出程序的总耗时。
     * <p>
     * 这里我们期望这个测验程序能够以任意顺序输出0~19这20个数字，然后打印出程序的总耗时，
     * <p>
     * <p>
     * 不仅是打印出了很多个null，而且打印出17行之后就不再打印更多数据，而且程序也就一直没有打印总耗时并结束了。为什么会发生这种情况呢？
     * <p>
     * 能稳定输出所有0~19的20个数字
     * 耗时长
     * 原因：但是如果队列在这200毫秒里发生了变化，那么线程也还是在休眠状态无法马上对变化做出响应。
     * 比如如果一个调用put()方法的线程因为队列已满而进入了200毫秒的休眠，那么即使队列已经被消费者线程清空了，
     * 它也仍然会忠实地等到200毫秒之后才会重新尝试向队列中插入元素，中间的这些时间就都被浪费了。
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // 创建一个大小为2的阻塞队列
        final BlockingQueue2 q = new BlockingQueue2(2);

        // 创建2个线程
        final int threads = 2;
        // 每个线程执行10次
        final int times = 10;

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

class BlockingQueue2 {

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
    private int count;

    /**
     * 指定队列大小的构造器
     *
     * @param capacity 队列大小
     */
    public BlockingQueue2(int capacity) {
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
        count++;
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
        count--;

        // 返回之前代码中取出的元素e
        return e;
    }

    /**
     * 将指定元素插入队列
     *
     * @param e 待插入的对象
     */
    public void put(Object e) throws InterruptedException {

        synchronized (this) {
            // 直到队列未满时才执行入队操作并跳出循环
            while (count == items.length) {
                this.wait();
            }
            // 执行入队操作，将对象e实际放入队列中
            enqueue(e);
            // 队列已满的情况下休眠200ms
            this.notifyAll();
        }
    }

    /**
     * 从队列中弹出一个元素
     *
     * @return 被弹出的元素
     */
    public Object take() throws InterruptedException {
        synchronized (this) {
            // 直到队列非空时才继续执行后续的出队操作并返回弹出的元素
            while (count == 0) {
                this.wait();

            }
            // 执行出队操作，将队列中的第一个元素弹出
            Object e = dequeue();

            // 队列为空的情况下休眠200ms
            this.notifyAll();
            return e;
        }
    }

}