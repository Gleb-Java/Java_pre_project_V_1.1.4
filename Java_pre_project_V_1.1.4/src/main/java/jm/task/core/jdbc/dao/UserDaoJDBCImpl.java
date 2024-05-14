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

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `user`.`user` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL,PRIMARY KEY (`id`),UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");

        } catch (SQLException e){

            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE `user`.`user`;");

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO `user`.`user` (name, lastname, age) VALUES (?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void removeUserById(long id) {

        try {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM `user`.`user` WHERE `id` = " + id);

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user`.`user`;");

            while (rs.next()) {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
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
