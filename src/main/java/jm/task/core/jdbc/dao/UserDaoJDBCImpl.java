package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(128) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        public void dropUsersTable () {
            try(Statement statement = Util.getConnection().createStatement()) {
                statement.executeUpdate("delete from users");
            } catch (ClassNotFoundException | SQLException e) {
                e.getMessage();
            }
        }

        public void saveUser (String name, String lastName,byte age){
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public void removeUserById ( long id){
            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)){
                statement.setLong(1, id);
                statement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.getMessage();
            }
        }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "SELECT id, name, lastname, age FROM users";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }
}


