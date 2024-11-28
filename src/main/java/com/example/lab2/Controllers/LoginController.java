package com.example.lab2.Controllers;

import com.example.lab2.Repositories.User;
import com.example.lab2.Repositories.UserRepository;
import com.example.lab2.Sevices.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    private final UserRepository userRepository;

    @Autowired
    public LoginController(LoginService loginService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

    public String userNotFound() {
        return "userNotFound";
    }

    @GetMapping("/login")
    public void login(String userLogin, String userPassword) {
        for(User user : userRepository.getUsers()){
            if(userLogin.equals(user.getUsername()) && userPassword.equals(user.getPassword())){
                loginService.login(user);
            }
        }
    }
}
