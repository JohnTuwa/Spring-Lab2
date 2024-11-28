package com.example.lab2.Sevices;

import com.example.lab2.Controllers.UserController;
import com.example.lab2.Repositories.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public void login(User user) {
        if (user.isAdmin()){
            System.out.println();
        }
    }
}
