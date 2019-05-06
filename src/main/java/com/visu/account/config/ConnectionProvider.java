package com.visu.account.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private ConnectionProvider() {
    }

    private static class Holder {
        static final ConnectionProvider INSTANCE = new ConnectionProvider();
    }

    private static Connection connection;

    public synchronized static void initConnection(String url, String username, String password) {
        connection = getConnection(url, username, password);
    }

    public synchronized static Connection getConnection() {
        return Holder.INSTANCE.connection;
    }

    private static Connection getConnection(String url, String username, String password) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
