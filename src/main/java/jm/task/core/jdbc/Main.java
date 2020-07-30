package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("DIEGO", "MARADONA", (byte) 54);
        userService.saveUser("STEVEN", "GERARD", (byte) 19);
        userService.saveUser("FRANCESCO", "DOUGLAS", (byte) 55);
        userService.saveUser("MICHAEL", "JACKSON", (byte) 30);

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
    }
}
