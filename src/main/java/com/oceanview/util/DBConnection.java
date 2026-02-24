package com.oceanview.util;
import java.sql.*;
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/oceanview_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = ""; // XAMPP = "" | password set කළොත් දාන්න
    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Database connected successfully!");
    }
    public static synchronized DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null || instance.connection.isClosed()) instance = new DBConnection();
        return instance;
    }
    public Connection getConnection() { return connection; }
}
