package com.pei.learn.basic.multiThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class T02_00_ThreadLocal_Connection {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        public Connection initialValue() {
            try {
                return DriverManager.getConnection("DB_URL");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
