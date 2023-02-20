package ru.yandex.practicum.filmorate.validations;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import java.time.LocalDate;
import java.time.Month;


@Slf4j
public class ValidateFilm {

    public void valFim (Film film) throws ValidationException {
        if (film.getName().trim().isEmpty()) {
            log.error("Неверно введено название: {}", film);
            throw new ValidationException("Field 'name' can't be empty");
        }
        if (film.getDescription().length()>200) {
            log.error("Неверное описание: {}", film);
            throw new ValidationException("Too long description");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, Month.DECEMBER, 28))) {
            log.error("Неправильная дата: {}", film);
            throw new ValidationException("Films not released yet");
        }
        if (film.getDuration().isNegative()) {
            log.error("Неверная продолжительность фильма: {}", film);
            throw new ValidationException("Please check duration, it can't be negative");
        }
    }
}
