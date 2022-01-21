package com.pei.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    public static void main(String[] args) throws IOException {
        // 1. 创建Selector对象
        Selector sel = Selector.open();

// 2. 向Selector对象绑定通道
        // a. 创建可选择通道，并配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        // b. 绑定通道到指定端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(8088);
        socket.bind(address);

        // c. 向Selector中注册感兴趣的事件
        server.register(sel, SelectionKey.OP_ACCEPT);
//        return sel;

// 3. 处理事件
        try {
            while (true) {
                // 该调用会阻塞，直到至少有一个事件就绪、准备发生
                sel.select();
                // 一旦上述方法返回，线程就可以处理这些事件
                Set<SelectionKey> keys = sel.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = (SelectionKey) iter.next();
                    iter.remove();
//                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
