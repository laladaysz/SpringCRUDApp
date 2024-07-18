package com.example.CRUD.security.services;

import com.example.CRUD.Models.User;
import com.example.CRUD.security.dtos.AuthenticationDTO;
import com.example.CRUD.security.dtos.TokenJwtDTO;
import com.example.CRUD.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  UserDetailsServiceImpl userDetailsImpl;

    @Autowired
    private UserService userService;

    public TokenJwtDTO loginAndCreateToken(AuthenticationDTO data) { //loga o user e gera o token dele
        try {
            User user = (User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username(), data.password())).getPrincipal();
            String tokenJwt = tokenService.gerarToken(user);
            return new TokenJwtDTO(tokenJwt);
        } catch (BadCredentialsException e ) {
            System.out.println("Something wrong with authentication: " + e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
