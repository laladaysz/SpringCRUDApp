package com.example.CRUD.Controller;



import com.example.CRUD.Exceptions.NotFoundException;
import com.example.CRUD.Models.User;
import com.example.CRUD.Repo.UserRepo;
import com.example.CRUD.dtos.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ApiControllers {

    @Autowired //deixa você usar os métodos do repository
    private UserRepo userRepo;

    @GetMapping
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();

    }

    @PostMapping("/save")
    public String saveUser(@RequestBody UserCreateDTO data) {
        User user = new User(data);
        userRepo.save(user);
        return "Saved new user!";
    }

    @PutMapping("update/{id}")
    public String updateUser(long id, User user) {
        User updatedUser = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Não achei"));
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return "User updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Já foi de F"));
        userRepo.delete(deleteUser);
        return "Delete user successfully!";
    }

}
