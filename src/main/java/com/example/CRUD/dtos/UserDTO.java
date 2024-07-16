package com.example.CRUD.dtos;

import com.example.CRUD.Models.User;
import com.example.CRUD.dtos.enums.UserRole;

public record UserDTO(Long id, String first_name, String last_name, int age, String occupation, UserRole role) {
    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(), user.getOccupation(), user.getRole());
    }
}
