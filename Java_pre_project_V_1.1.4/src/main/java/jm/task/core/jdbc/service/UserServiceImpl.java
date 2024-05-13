package jm.task.core.jdbc.service;

import com.mysql.cj.util.Util;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.util.List;


public class UserServiceImpl implements UserService {
    public void createUsersTable() {
       Util util = Util.getInstance();
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
