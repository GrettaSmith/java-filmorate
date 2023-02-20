package ru.yandex.practicum.filmorate.validations;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
@Slf4j
public class ValidateUser {

    public void valUser (User user) throws ValidationException {
        if (user.getEmail().isEmpty()||!user.getEmail().contains("@")) {
            log.error("Неверный e-mail: {}", user);
            throw new ValidationException("Check e-mail address. It can't be empty and must contain '@'");
        }
        if (user.getLogin().isEmpty()||user.getLogin().contains(" ")) {
            log.error("Неверный логин: {}", user);
            throw new ValidationException("Check your login. It can't be empty or contains spaces");
        } if (user.getName().isEmpty()) {
            log.error("Неверно указано имя. Заменено на логин: {}", user);
            user.setName(user.getLogin());
        } if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Неверная дата рождения: {}", user);
            throw new ValidationException("Error! Please check your date of birthday");
        }
    }
}
