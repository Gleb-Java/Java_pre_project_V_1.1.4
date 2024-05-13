package jm.task.core.jdbc.service;

import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {

    public void createUsersTable() {

       Util util = Util.getInstance();
       Connection conn = util.getConn();

       try {

           Statement stmt = conn.createStatement();
           stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `user`.`user` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL,PRIMARY KEY (`id`),UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");

       } catch (SQLException e){

           e.printStackTrace();

       }

    }

    public void dropUsersTable() {

        Util util = Util.getInstance();
        Connection conn = util.getConn();

        try {

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS`user`.`user`;");

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = Util.getInstance();
        Connection conn = util.getConn();

        try {

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO `user`.`user` (name, lastname, age) VALUES ('" + name + "', '" + lastName+ "', " + age +")");
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public void removeUserById(long id)
    {
        Util util = Util.getInstance();
        Connection conn = util.getConn();

        try {

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM `user`.`user` WHERE `id` = " + id);

        } catch (SQLException e){

            e.printStackTrace();

        }
    }

    public List<User> getAllUsers() {

        Util util = Util.getInstance();
        Connection conn = util.getConn();
        List<User> users = new ArrayList<User>();

        try {

            Statement stmt = conn.createStatement();
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

    public void cleanUsersTable() {
        int temp = 1;
        while (temp < 10000){
            removeUserById(temp);
            temp++;
        }
    }
}
