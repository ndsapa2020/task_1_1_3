package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("MARGARET", "TETCHER", (byte) 55);
        userService.saveUser("MICHAEL", "GORBACHEV", (byte) 58);
        userService.saveUser("MICHAEL", "JACKSON", (byte) 30);
        userService.saveUser("ELIZABETH", "TAYLOR", (byte) 25);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
