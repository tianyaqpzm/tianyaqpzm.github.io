package com.pei.learn.basic.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * https://zhuanlan.zhihu.com/p/64156753
 */
public class T00_00_BlockQueue {

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
     * 原因就是在我们实现的这个阻塞队列中完全没有线程同步机制，所以同时并发进行的4个线程（2个生产者和2个消费者）会同时执行阻塞队列的put()和take()方法。
     * 这就可能会导致各种各样并发执行顺序导致的问题，
     * <p>
     * 比如两个生产者同时对阻塞队列进行插入操作，有可能就会在putIndex没更新的情况下对同一下标位置又插入了一次数据，导致了数据还没被消费就被覆盖了；
     * <p>
     * 而两个消费者也可能会在takeIndex没更新的情况下又获取了一次已经被清空的位置，导致打印出了null。
     * <p>
     * 最后因为这些原因都有可能会导致消费者线程最后还没有弹出20个数字count就已经为0了，这时消费者线程就会一直处于阻塞状态无法退出了。
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

class BlockingQueue {

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
    public BlockingQueue(int capacity) {
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
        while (true) {
            // 直到队列未满时才执行入队操作并跳出循环
            if (count != items.length) {
                // 执行入队操作，将对象e实际放入队列中
                enqueue(e);
                break;
            }

            // 队列已满的情况下休眠200ms
            Thread.sleep(200L);
        }
    }

    /**
     * 从队列中弹出一个元素
     *
     * @return 被弹出的元素
     */
    public Object take() throws InterruptedException {
        while (true) {
            // 直到队列非空时才继续执行后续的出队操作并返回弹出的元素
            if (count != 0) {
                // 执行出队操作，将队列中的第一个元素弹出
                return dequeue();
            }

            // 队列为空的情况下休眠200ms
            Thread.sleep(200L);
        }
    }

}