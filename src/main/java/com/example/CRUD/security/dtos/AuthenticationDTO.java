package com.example.CRUD.security.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String username, @NotBlank String password) {

}
