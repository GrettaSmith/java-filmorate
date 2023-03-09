package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DuplicateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;

public interface FilmStorage extends Storage <Film>{
     final HashMap<Integer, Film> filmsList = new HashMap<>();
    List<Film> getAll();

    Film get(int id);

    Film create(Film film) throws DuplicateException;

    Film update(Film film);

}