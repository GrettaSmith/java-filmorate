package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DuplicateException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Set;

public interface UserStorage extends Storage <User>{

    List<User> getAll();

    User get(int id);

    User create(User user);

    User update(User user);

}