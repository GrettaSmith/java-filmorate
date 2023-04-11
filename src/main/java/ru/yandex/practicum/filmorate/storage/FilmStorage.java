package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.DuplicateException;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmStorage extends Storage<Film>  {

    List<Film> getFilmsList() throws SQLException;

    Film getFilmById(int id);

    Film addFilm(Film film) throws DuplicateException;

    Film updateFilm(Film film);

    Film addFilmLike(Integer id, Integer userId);

    Film deleteFilmLike(Integer id, Integer userId);

    List<Film> getMostPopularFilms(int count) throws SQLException;
}
