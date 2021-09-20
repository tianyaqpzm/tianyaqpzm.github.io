package com.pei.learn.safe;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SslEServer {

    public static void main(String[] args) throws IOException {
        SSLServerSocketFactory aDefault = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket = (SSLServerSocket) aDefault.createServerSocket(443);
        Socket accept = sslServerSocket.accept();

        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        String input;
        while ((input = in.readLine()) != null) {
            System.out.println(input);
            printWriter.println(input);
        }
    }
}

class SslEClient {

    public static void main(String[] args) throws IOException {
        SSLSocketFactory aDefault = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) aDefault.createSocket("127.0.0.1", 443);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        //传给server的值
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 接受键盘输入
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = stdin.readLine()) != null) {
            printWriter.println(input);

//            System.out.println(in.readLine());
        }
    }
}

