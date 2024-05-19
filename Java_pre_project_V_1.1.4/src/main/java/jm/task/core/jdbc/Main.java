package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov", (byte) 5);
        userService.saveUser("Gleb", "Bushelenkov", (byte) 22);
        userService.saveUser("Pavel", "Chigoryaev", (byte) 23);
        userService.saveUser("Andrey", "Strogiy", (byte) 23);
        userService.saveUser("Polina", "Gasayeva", (byte) 21);

        List<User> userList = userService.getAllUsers();

        for (User users : userList) {
            System.out.println(users.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
