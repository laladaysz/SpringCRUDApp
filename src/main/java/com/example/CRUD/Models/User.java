package com.example.CRUD.Models;


import com.example.CRUD.dtos.UserCreateDTO;
import com.example.CRUD.dtos.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User  implements UserDetails

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String password;
    @Column(name = "first_name") // name usado pois a migration n identifica qnd tem a letra maiuscula no meio
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private int age;
    @Column
    private String occupation;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(UserCreateDTO data, String password) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.age = data.age();
        this.occupation = data.occupation();
        this.role = UserRole.ROLE_USER;
        this.username = data.username();
        this.password = password;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
