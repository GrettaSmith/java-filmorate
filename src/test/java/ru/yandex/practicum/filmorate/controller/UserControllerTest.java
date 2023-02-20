package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {
    @Test
    void create() throws ValidationException {
        User user1 = new User("login","name","email@mail.com", LocalDate.of(1996,1,1));
        User user2 = new User("login","name","email@mail.com", LocalDate.of(1996,1,1));
        user2.setId(1);
        user2.setName("name");
        UserController userController = new UserController();
        User user3 = userController.addNewUser(user1);
        assertEquals(user2.getId(),user3.getId());
        assertEquals(user2.getName(),user3.getName());
        assertEquals(user2.getLogin(),user3.getLogin());
        assertEquals(user2.getEmail(),user3.getEmail());
        assertEquals(user2.getBirthday(),user3.getBirthday());
    }

    @Test
    void update() throws ValidationException {
        UserController userController = new UserController();
        User user1 = new User("login","name","email@mail.com", LocalDate.of(1996,1,1));
        userController.addNewUser(user1);
        User user2 = new User("newLogin","newName","newEmail@mail.com", LocalDate.of(1996,4,4));
        user2.setId(1);
        user2.setName("name");
        User user3 = userController.updateOldUser(user2);
        assertEquals(user2.getId(),user3.getId());
        assertEquals(user2.getName(),user3.getName());
        assertEquals(user2.getLogin(),user3.getLogin());
        assertEquals(user2.getEmail(),user3.getEmail());
        assertEquals(user2.getBirthday(),user3.getBirthday());
    }

    @Test
    void findAll() throws ValidationException {
        UserController userController = new UserController();
        List<User> users = userController.getAllUsers();
        assertEquals(0,users.size());
        User user1 = new User("login","name","email@mail.com", LocalDate.of(1996,1,1));
        userController.addNewUser(user1);
        users = userController.getAllUsers();
        assertEquals(1,users.size());
    }
}
