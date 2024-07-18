package com.example.CRUD.security.services;

import com.example.CRUD.Models.User;
import com.example.CRUD.dtos.UserCreateDTO;
import com.example.CRUD.dtos.UserDTO;
import com.example.CRUD.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO registerGuest(UserCreateDTO data) {
        User user = new User(data, passwordEncoder.encode(data.password()));
        userService.saveUser(user);

        return new UserDTO(user);
    }
}
