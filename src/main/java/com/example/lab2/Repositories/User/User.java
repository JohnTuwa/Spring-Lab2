package com.example.lab2.Repositories.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String password;
    private boolean isAdmin;
}