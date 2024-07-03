package com.example.CRUD.Repo;

import com.example.CRUD.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {


}
