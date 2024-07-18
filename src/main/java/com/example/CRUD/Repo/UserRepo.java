package com.example.CRUD.Repo;

import com.example.CRUD.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
