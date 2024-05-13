package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();

        user.saveUser("Gleb", "Bushelenkov", (byte) 22);
        user.saveUser("Pavel", "Chigoryaev", (byte) 23);
        user.saveUser("Andrey", "Strogiy", (byte) 23);
        user.saveUser("Polina", "Gasayeva", (byte) 21);

        List<User> userList = user.getAllUsers();

        for (User u : userList) {
            System.out.println(u.toString());
        }

        user.cleanUsersTable();
        user.dropUsersTable();

    }
}
