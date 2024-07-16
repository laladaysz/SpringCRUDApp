package com.example.CRUD.services;

import com.example.CRUD.Exceptions.NotFoundException;
import com.example.CRUD.Models.User;
import com.example.CRUD.Repo.UserRepo;
import com.example.CRUD.dtos.UserCreateDTO;
import com.example.CRUD.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDTO saveUser(UserCreateDTO data) {
        User user = new User(data);
        userRepo.save(user);
        return new UserDTO(user);
    }

    public List<UserDTO> getAll() {
      return userRepo.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO updateUser(Long id, UserCreateDTO data) {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setOccupation(data.occupation());
        user.setAge(data.age());
        userRepo.save(user);
        return new UserDTO(user);
    }

    public String deleteUser(Long id) {
        User deleteUser = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Já foi de F"));
        userRepo.delete(deleteUser);

        return "Delete user successfully! \n" + new UserDTO(deleteUser);
    }
}
