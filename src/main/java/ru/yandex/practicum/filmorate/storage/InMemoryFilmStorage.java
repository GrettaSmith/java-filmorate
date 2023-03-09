package ru.yandex.practicum.filmorate.storage;


import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.DuplicateException;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.util.LikesComparator;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryFilmStorage implements FilmStorage  {




    private int id = 0;

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(filmsList.values());
    }

    @Override
    public Film get(int id) {
        if (filmsList.containsKey(id)) {
            return filmsList.get(id);
        }
        throw new NotFoundException("Film not found!"); // throw err
    }

    @Override
    public Film create(Film film) throws DuplicateException {
        id += 1;
        film.setId(id);
        if (filmsList.containsValue(film)) {
            throw new DuplicateException("Film already added!");
        }
        filmsList.put(id, film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (filmsList.containsKey(film.getId())) {
            filmsList.put(film.getId(), film);
        } else {
            throw new NotFoundException("Film not found!");
        }
        return film;
    }

}