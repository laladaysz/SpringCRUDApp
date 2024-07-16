package com.example.CRUD.Controller;



import com.example.CRUD.Exceptions.NotFoundException;
import com.example.CRUD.Models.User;
import com.example.CRUD.Repo.UserRepo;
import com.example.CRUD.dtos.UserCreateDTO;
import com.example.CRUD.dtos.UserDTO;
import com.example.CRUD.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController("/users")
public class ApiControllers {

    @Autowired
    private UserService service;

    @GetMapping
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("/get-all") //atualizado
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save") //atualizado
    @Transactional
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCreateDTO data, UriComponentsBuilder uriBuilder) {
        UserDTO user = service.saveUser(data);
        URI uri = uriBuilder.path(("/users/{id}")).buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);

    }

    @PutMapping("update/{id}") //atualizado
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserCreateDTO user) {
        return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") //atualizado
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }

}
