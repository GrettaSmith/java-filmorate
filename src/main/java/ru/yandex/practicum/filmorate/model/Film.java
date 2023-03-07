package ru.yandex.practicum.filmorate.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.filmorate.validator.CorrectDate;

import javax.validation.constraints.*;
import javax.validation.executable.ValidateOnExecution;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ValidateOnExecution
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Film {

     Integer id;
    @NotEmpty(message = "Empty name!")
    @NotNull
     String name;
    @Size(min = 2, max = 200, message = "Description should be not empty & less then 200")
    @NotEmpty
     String description;

    @PastOrPresent(message = "Incorrect date!")
    @CorrectDate
     LocalDate releaseDate;
    @Positive
     Long duration;

    private final Set<Integer> userLikes = new HashSet<>();
}