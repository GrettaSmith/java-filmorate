package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.DuplicateException;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.util.LikesComparator;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final Comparator<Film> likesComparator = new LikesComparator();
    protected final Set<Film> filmsRating = new TreeSet<>(likesComparator);

    public List<Film> getFilmsList() {
        return filmStorage.getAll();
    }

    public Film getFilmById(Integer id) {
        return filmStorage.get(id);
    }

    public Film addFilm(Film film) throws DuplicateException {
        return filmStorage.create(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.update(film);
    }

    public Film likeFilm(Integer id, Integer userId) {
        userStorage.get(userId); // немного костыль, но как мне кажется, самы простой способ проверить, что юзер есть - если его нет, то будет исключние
        Film film = filmStorage.get(id);
        film.getUserLikes().add(userId);
        filmStorage.update(film);
        return film;
    }

    public Film unlikeFilm(Integer id, Integer userId) {
        Film film = filmStorage.get(id);
        if (!film.getUserLikes().contains(userId)) {
            throw new NotFoundException("Film not found!");
        }
        film.getUserLikes().remove(userId);
        filmStorage.update(film);
        return film;
    }

    public List<Film> getMostPopularFilms(int count) {
        filmsRating.addAll(filmStorage.filmsList.values());
        return filmsRating.stream().limit(count).collect(Collectors.toList());
    }
}