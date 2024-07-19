package com.example.CRUD.Controller;



import com.example.CRUD.Exceptions.NotFoundException;
import com.example.CRUD.Models.User;
import com.example.CRUD.Repo.UserRepo;
import com.example.CRUD.dtos.UserCreateDTO;
import com.example.CRUD.dtos.UserDTO;
import com.example.CRUD.security.dtos.AuthenticationDTO;
import com.example.CRUD.security.dtos.TokenJwtDTO;
import com.example.CRUD.security.services.AuthenticationService;
import com.example.CRUD.security.services.AuthorizationService;
import com.example.CRUD.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class ApiControllers {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("/get-all") //atualizado
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenJwtDTO> login (@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        return new ResponseEntity<>(authenticationService.loginAndCreateToken(authenticationDTO), HttpStatus.OK);
    }

    @PostMapping("/save") //atualizado
    @Transactional
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCreateDTO data) {
       return new ResponseEntity<>(authorizationService.registerGuest(data), HttpStatus.OK);
    }

    @PutMapping("/update/{id}") //atualizado
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserCreateDTO user) {
        return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") //atualizado
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }

}
