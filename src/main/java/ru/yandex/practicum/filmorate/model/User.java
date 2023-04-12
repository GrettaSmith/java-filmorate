package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import javax.validation.executable.ValidateOnExecution;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@ValidateOnExecution
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
     Integer id;
    @Email(message = "Incorrect Email!")
     String email;
    @NotBlank(message = "Incorrect login!")
    @Pattern(regexp = "^\\S*$", message = "Incorrect login!")
     String login;

     String name;

    @NotNull
    @PastOrPresent(message = "Incorrect date!")
     LocalDate birthday;

     final Set<Integer> friends = new HashSet<>();
     final Set<Integer> unacceptedFriends = new HashSet<>();
}