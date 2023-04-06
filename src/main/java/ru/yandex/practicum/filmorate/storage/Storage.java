package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DuplicateException;

import java.util.List;

public interface Storage<T> {
     T create(T data) throws DuplicateException;

    T update(T data);

    T get(int id);

    List<T> getAll();
}
