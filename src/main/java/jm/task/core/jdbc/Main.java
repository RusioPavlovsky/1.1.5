package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ruslan", "Pavlov", (byte) 21);
        System.out.println("User с именем Ruslan добавлен в базу данных");
        userService.saveUser("Sultan", "Suleyman", (byte) 21);
        System.out.println("User с именем Sultan добавлен в базу данных");
        userService.saveUser("John", "Snow", (byte) 18);
        System.out.println("User с именем John добавлен в базу данных");
        userService.saveUser("Omar", "Hayam", (byte) 99);
        System.out.println("User с именем Omar добавлен в базу данных");
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
