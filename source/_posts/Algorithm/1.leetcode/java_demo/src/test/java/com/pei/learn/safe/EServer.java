package com.pei.learn.safe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket accept = serverSocket.accept();

        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        String input;
        while ((input = in.readLine()) != null) {
            System.out.println(input);
            printWriter.println(input);
        }
    }
}

class EClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

//        BufferedReader out = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        String input;

        while ((input = stdin.readLine()) != null) {
            printWriter.println(input);
            System.out.println(input);
        }
    }
}

