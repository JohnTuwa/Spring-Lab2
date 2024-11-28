package com.example.lab2.Repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User(0, "admin", "admin", true));
        users.add(new User(1, "user", "user", false));
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean checkRights(User user) {
        return user.isAdmin();
    }
}
