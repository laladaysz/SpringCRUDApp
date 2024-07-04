package com.example.CRUD.Models;


import com.example.CRUD.dtos.UserCreateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name") // name usado pois a migration n identifica qnd tem a letra maiuscula no meio
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private int age;
    @Column
    private String occupation;


    public User(UserCreateDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.age = data.age();
        this.occupation = data.occupation();
    }
}
