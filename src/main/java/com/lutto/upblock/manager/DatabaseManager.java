package com.lutto.upblock.manager;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private final String HOST = Dotenv.configure().load().get("HOST");
    private final int PORT = Integer.parseInt(Dotenv.configure().load().get("PORT"));
    private final String DATABASE = Dotenv.configure().load().get("DATABASE");
    private final String USERNAME = Dotenv.configure().load().get("DBUSERNAME");
    private final String PASSWORD = Dotenv.configure().load().get("PASSWORD");

    private Connection connection;

    public void connect() throws SQLException {

        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);

    }

    public Boolean isConnected() { return connection != null; }

    public void disconnect() {

        if (!isConnected()) return;

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

}
