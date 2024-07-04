package com.example.CRUD.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO (@NotBlank String firstName, @NotBlank String lastName, @NotBlank int age, @NotBlank String occupation) {

}
