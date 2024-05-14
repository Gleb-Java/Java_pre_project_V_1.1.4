package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getInstance().getConn();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {


        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL,PRIMARY KEY (`id`),UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");

        } catch (SQLException e){

            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE user;");

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastname, age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void removeUserById(long id) {

        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE `id` = " + id);

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `user`.`user`;");

            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);

            }

        } catch (SQLException e){

            e.printStackTrace();

        }

        return users;
    }

    public void cleanUsersTable() {int temp = 1;
        while (temp < 10000){
            removeUserById(temp);
            temp++;
        }
    }
}
