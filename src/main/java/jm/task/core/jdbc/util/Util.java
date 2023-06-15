package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String userName = "root";
    private static final String password = "1034";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        return conn;
    }
}
