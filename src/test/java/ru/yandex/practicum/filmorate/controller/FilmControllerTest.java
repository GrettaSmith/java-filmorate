package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmControllerTest {
    @Test
    void addNewFilmTest() throws ValidationException {
        Film film1 = new Film("name1", "description1", LocalDate.of(2022, 6, 1), Duration.ofMinutes(60));
        Film film2 = new Film("name1", "description1", LocalDate.of(2022, 6, 1), Duration.ofMinutes(60));
        film2.setId(1);
        FilmController filmController = new FilmController();
        Film film3 = filmController.addNewFilm(film1);
        assertEquals(film2.getId(), film3.getId());
        assertEquals(film2.getName(), film3.getName());
        assertEquals(film2.getDescription(), film3.getDescription());
        assertEquals(film2.getReleaseDate(), film3.getReleaseDate());
        assertEquals(film2.getDuration(), film3.getDuration());
    }

    @Test
    void updateOldFilm() throws ValidationException {
        FilmController filmController = new FilmController();
        Film film1 = new Film("name1","description1", LocalDate.of(2022,6,1),Duration.ofMinutes(60));
        filmController.addNewFilm(film1);
        Film film2 = new Film("name2","description2", LocalDate.of(2022,6,2),Duration.ofMinutes(120));
        film2.setId(1);
        Film film3 = filmController.updateOldFilm(film2);
        assertEquals(film2.getId(), film3.getId());
        assertEquals(film2.getName(), film3.getName());
        assertEquals(film2.getDescription(), film3.getDescription());
        assertEquals(film2.getReleaseDate(), film3.getReleaseDate());
        assertEquals(film2.getDuration(), film3.getDuration());
    }

    @Test
    void findAll() throws ValidationException {
        FilmController filmController = new FilmController();
        ArrayList<Film> films = filmController.getAllFilms();
        assertEquals(0,films.size());
        Film film1 = new Film("name1","description1", LocalDate.of(2022,6,1),Duration.ofMinutes(60));
        filmController.addNewFilm(film1);
        films = filmController.getAllFilms();
        assertEquals(1,films.size());
    }
}
