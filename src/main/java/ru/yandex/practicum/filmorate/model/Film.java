package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.validator.CorrectDate;

import javax.validation.constraints.*;
import javax.validation.executable.ValidateOnExecution;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@ValidateOnExecution
@FieldDefaults (makeFinal=true, level= AccessLevel.PRIVATE)
public class Film {


    Integer id;
    String name;
    @Size(min = 2, max = 200, message = "Description should be not empty & less then 200")
    String description;

    @PastOrPresent(message = "Incorrect date!")
    @CorrectDate
    LocalDate releaseDate;
    @Positive
    Long duration;
}