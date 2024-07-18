package com.example.CRUD.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO (@NotBlank String username, @NotBlank String password, @NotBlank String firstName, @NotBlank String lastName, @NotBlank int age, @NotBlank String occupation) {

}
